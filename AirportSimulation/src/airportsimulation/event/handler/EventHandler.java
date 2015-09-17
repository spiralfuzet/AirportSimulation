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
import airportsimulation.schedule.StateFlag;
import java.util.concurrent.Callable;

/**
 *
 * @author tothm
 */
public abstract class EventHandler implements Callable<StateFlag> {

    public static final Integer EVENT_LOOP_LENGTH_IN_MILISECONDS = 100;

    protected final Airplane airplane;
    protected final Schedule schedule;

    private Integer secondsLeftFromEvent;

    public EventHandler(final Airplane airplane, final Schedule schedule) {
        this.airplane = airplane;
        this.schedule = schedule;

        this.secondsLeftFromEvent = schedule.getInStatusSecs() * 1000;
    }

    @Override
    public final StateFlag call() throws Exception {
        secondsLeftFromEvent -= EVENT_LOOP_LENGTH_IN_MILISECONDS;
        if (secondsLeftFromEvent > 0) {
            handleEvent();
            Thread.sleep(EVENT_LOOP_LENGTH_IN_MILISECONDS);
            return StateFlag.RUNNING;
        }
        return StateFlag.ENDED;
    }

    protected abstract void handleEvent();

}
