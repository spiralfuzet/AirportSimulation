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
import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.schedule.Schedule;
import airportsimulation.schedule.StateFlag;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class InFlightEventHandlerTest {

    @Test
    public void shouldMoveAirplane() throws Exception {
        Airplane a = new Airplane();
        a.setActFuelLevel(100.0d);
        a.setConsumption(10.0d);

        Schedule s = new Schedule(null, AirplaneStatusFlag.IN_FLIGHT, null, 0.3d);

        InFlightEventHandler flightEventHandler = new InFlightEventHandler(a, s);

        assertThat(flightEventHandler.call(), is(StateFlag.RUNNING));
        assertThat(a.getActFuelLevel(), is(90.0d));

        assertThat(flightEventHandler.call(), is(StateFlag.RUNNING));
        assertThat(a.getActFuelLevel(), is(80.0d));

        assertThat(flightEventHandler.call(), is(StateFlag.ENDED));
        assertThat(a.getActFuelLevel(), is(70.0d));
    }

}
