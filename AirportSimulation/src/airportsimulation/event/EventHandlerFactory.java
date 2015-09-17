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
import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.event.handler.EventHandler;
import airportsimulation.schedule.Schedule;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author tothm
 */
public class EventHandlerFactory {

    public EventHandler create(final Airplane airplane, final Schedule schedule) throws EventHandlerException {
        try {
            final AirplaneStatusFlag flag = schedule.getStatusFlag();
            final String eventHandlerName
                    = this.getClass().getPackage().getName() + "." + flag.getEventHandlerName();

            Class<?> eventHandlerClass = Class.forName(eventHandlerName);
            Constructor constructor = eventHandlerClass.getConstructor(Airplane.class, Schedule.class);

            return (EventHandler) constructor.newInstance(airplane, schedule);

        } catch (ClassNotFoundException | NoSuchMethodException |
                SecurityException | InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex) {
            throw new EventHandlerException("Cannot create event handler due to: \n" + ex);
        }
    }
}
