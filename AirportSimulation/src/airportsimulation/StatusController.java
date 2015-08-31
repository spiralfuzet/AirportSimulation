/*
 * Copyright (c) 2015 Rendszergazda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */
package airportsimulation;

import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.utils.Observer;

/**
 *
 * @author tothm
 */
public class StatusController extends Observer {

    void setState(AirplaneStatusFlag airplaneStatusFlag) {
        notify_all();
    }

}
