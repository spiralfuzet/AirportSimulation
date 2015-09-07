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
import java.util.Queue;
import java.util.concurrent.Callable;

/**
 *
 * @author tothm
 */
public class ScheduleController implements Callable<ScheduleStateFlag> {

    private final StatusController statusController;
    private final Queue<Schedule> schedules;

    public ScheduleController(StatusController statusController, Queue<Schedule> schedules) {
        this.statusController = statusController;
        this.schedules = schedules;
    }

    public Airplane getAirplane() {
        return statusController.getAirplane();
    }

    @Override
    public ScheduleStateFlag call() throws Exception {
        if (schedules.isEmpty()) {
            return ScheduleStateFlag.ENDED;
        }
        statusController.setSchedule(schedules.element());
        schedules.remove();
        return ScheduleStateFlag.RUNNING;
    }

}
