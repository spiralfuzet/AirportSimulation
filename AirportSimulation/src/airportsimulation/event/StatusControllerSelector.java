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
package airportsimulation.event;

import airportsimulation.airplane.Airplane;
import airportsimulation.event.handler.EventHandler;
import airportsimulation.schedule.Schedule;
import airportsimulation.utils.Observable;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author tothm
 */
public class StatusControllerSelector extends Observable {

    private final Airplane airplane;
    private final StatusController statusController;
    private final EventHandlerFactory eventHandlerFactory;
    private Schedule currentSchedule;

    public StatusControllerSelector(Airplane airplane, ExecutorService executorService) {
        this.airplane = airplane;
        this.statusController = new StatusController(executorService);
        eventHandlerFactory = new EventHandlerFactory();
        this.currentSchedule = null;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Schedule getCurrentSchedule() {
        return currentSchedule;
    }

    public void setSchedule(final Schedule currentSchedule) throws EventHandlerException {
//        try {
            airplane.setState(currentSchedule.getStatusFlag());
            this.currentSchedule = currentSchedule;
            notifyObservers();

            EventHandler eventHandler = eventHandlerFactory.create(airplane, currentSchedule);
            statusController.startEvent(eventHandler);

            //Thread.sleep(currentSchedule.getInStatusSecs() * 1000);

//        } catch (InterruptedException ex) {
//            System.err.println("Cannot execute schedule for " + airplane + " due to:\n" + ex);
//        }
    }
}
