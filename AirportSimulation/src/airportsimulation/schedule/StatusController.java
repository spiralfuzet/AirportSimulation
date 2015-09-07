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
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author tothm
 */
public class StatusController {

    private final Airplane airplane;

    public StatusController(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setSchedule(final Schedule currentSchedule) {
        try {
            airplane.setState(currentSchedule.getStatusFlag());

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println(timeStamp + " " + airplane + " " + currentSchedule.getAirportId());

            Thread.sleep(currentSchedule.getInStatusSecs() * 1000);
        } catch (InterruptedException ex) {
            System.err.println("Cannot execute schedule for " + airplane + " due to:\n" + ex);
        }
    }
}
