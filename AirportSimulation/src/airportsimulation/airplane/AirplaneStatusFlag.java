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
package airportsimulation.airplane;

/**
 *
 * @author tothm
 */
public enum AirplaneStatusFlag {
    IN_FLIGHT("handler.InFlightEventHandler"),
    LANDING("handler.LandingEventHandler"),
    TAKE_OFF("handler.TakeOffEventHandler"),
    MAINTENANCE("handler.MaintenanceEventHandler"),
    LOAD("handler.LoadEventHandler"),
    UNLOAD("handler.UnloadEventHandler"),
    TAXI("handler.TaxiEventHandler");

    private final String eventHandlerName;

    private AirplaneStatusFlag(final String eventHandlerName) {
        this.eventHandlerName = eventHandlerName;
    }

    public String getEventHandlerName() {
        return eventHandlerName;
    }
};
