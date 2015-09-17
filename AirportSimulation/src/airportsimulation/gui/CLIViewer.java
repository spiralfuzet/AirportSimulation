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
package airportsimulation.gui;

import airportsimulation.event.StatusControllerSelector;
import airportsimulation.utils.Observable;
import airportsimulation.utils.Observer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author tothm
 */
public class CLIViewer implements Observer {

    @Override
    public void update(Observable observable) {
        if (!(observable instanceof StatusControllerSelector)) {
            return;
        }
        StatusControllerSelector sc = (StatusControllerSelector) observable;
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp + " " + sc.getAirplane() + " " + sc.getCurrentSchedule().getAirportId());
    }

}
