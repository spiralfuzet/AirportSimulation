/*
 * Copyright (c) 2015 tothm.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    tothm - initial API and implementation and/or initial documentation
 */
package airportsimulation.schedule;

import airportsimulation.airplane.Airplane;
import airportsimulation.event.StatusControllerSelector;
import airportsimulation.event.handler.EventHandler;
import airportsimulation.gui.CLIViewer;
import airportsimulation.utils.Builder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author tothm
 */
public class Scheduler {

    private final Map<String, Queue<Schedule>> schedules;
    private final Map<String, ScheduleController> scheduleControllers;
    private final Map<String, Future> scheduleStateFutures;
    private final ExecutorService executorService;

    public Scheduler(Builder<Airplane> airplaneBuilder, Builder<Schedule> scheduleReader) {
        schedules = new HashMap<>();
        scheduleControllers = new HashMap<>();
        scheduleStateFutures = new HashMap<>();
        executorService = Executors.newScheduledThreadPool(4);

        queueSchedules(scheduleReader);
        createControllers(airplaneBuilder);
    }

    private void queueSchedules(Builder<Schedule> scheduleReader) {
        while (scheduleReader.hasNext()) {
            Schedule s = scheduleReader.getNext();
            final String airplaneId = s.getAirplaneId();
            if (!schedules.containsKey(airplaneId)) {
                schedules.put(airplaneId, new LinkedList<Schedule>());
            }
            schedules.get(airplaneId).add(s);
        }
    }

    private void createControllers(Builder<Airplane> airplaneBuilder) {
        while (airplaneBuilder.hasNext()) {
            Airplane airplane = airplaneBuilder.getNext();

            StatusControllerSelector statusController = new StatusControllerSelector(airplane, executorService);
            statusController.attach(new CLIViewer());

            final String airplaneId = airplane.getId();
            if (!schedules.containsKey(airplaneId)) {
                continue;
            }
            ScheduleController scheduleController = new ScheduleController(
                    statusController, schedules.get(airplaneId));
            scheduleControllers.put(airplaneId, scheduleController);
            scheduleStateFutures.put(airplaneId, null);
        }
    }

    public void startSchedules() {
        Set<String> scheduleInProgressIdSet = new HashSet<>();
        while (true) {
            submitNextTask(scheduleInProgressIdSet);
            final int endedScheduleCount = checkTaskFinished(scheduleInProgressIdSet);
            if (endedScheduleCount == scheduleStateFutures.size()) {
                break;
            }
        }
        executorService.shutdown();
    }

    private int checkTaskFinished(Set<String> scheduleInProgressIdSet) {
        int endedScheduleCnt = 0;
        for (String airplaneId : scheduleStateFutures.keySet()) {
            Future<StateFlag> scheduleStateFuture = scheduleStateFutures.get(airplaneId);
            if (scheduleStateFuture == null) {
                scheduleInProgressIdSet.remove(airplaneId);
                ++endedScheduleCnt;
                continue;
            }
            try {
                StateFlag stateFlag = scheduleStateFuture.get(
                        EventHandler.EVENT_LOOP_LENGTH_IN_MILISECONDS, TimeUnit.MILLISECONDS);
                if (stateFlag.equals(StateFlag.ENDED)) {
                    ++endedScheduleCnt;
                } else {
                    scheduleInProgressIdSet.remove(airplaneId);
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.err.println("Cannot get schedule state future value due to:\n" + ex);
            } catch (TimeoutException ex) {
            }
        }
        return endedScheduleCnt;
    }

    private void submitNextTask(Set<String> scheduleInProgressIdSet) {
        for (String airplaneId : scheduleControllers.keySet()) {
            if (scheduleInProgressIdSet.contains(airplaneId)) {
                continue;
            }
            ScheduleController controller = scheduleControllers.get(airplaneId);
            scheduleStateFutures.remove(airplaneId);
            scheduleStateFutures.put(airplaneId, executorService.submit(controller));
            scheduleInProgressIdSet.add(airplaneId);
        }
    }
}
