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
package airportsimulation.event.handler;

import airportsimulation.airplane.Airplane;
import airportsimulation.schedule.Schedule;

/**
 *
 * @author tothm
 */
public class LoadEventHandler  extends EventHandler {

    public LoadEventHandler(Airplane airplane, Schedule schedule) {
        super(airplane, schedule);
    }

    @Override
    protected void handleEvent() {

    }

}
