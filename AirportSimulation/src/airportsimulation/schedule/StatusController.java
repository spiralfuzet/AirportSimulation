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
import airportsimulation.utils.Observable;

/**
 *
 * @author tothm
 */
public class StatusController extends Observable {

    private final Airplane airplane;
    private Schedule currentSchedule;

    public StatusController(Airplane airplane) {
        this.airplane = airplane;
        this.currentSchedule = null;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Schedule getCurrentSchedule() {
        return currentSchedule;
    }

    public void setSchedule(final Schedule currentSchedule) {
        try {
            airplane.setState(currentSchedule.getStatusFlag());
            this.currentSchedule = currentSchedule;
            notifyObservers();

            Thread.sleep(currentSchedule.getInStatusSecs() * 1000);
        } catch (InterruptedException ex) {
            System.err.println("Cannot execute schedule for " + airplane + " due to:\n" + ex);
        }
    }
}
