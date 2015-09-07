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
import airportsimulation.airplane.AirplaneStatusFlag;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class StatusControllerTest {

    @Test
    public void shouldChangeStatus() throws InterruptedException {

        Airplane a = new Airplane();
        a.setState(AirplaneStatusFlag.LOAD);

        StatusController sc = new StatusController(a);

        assertThat(a.getState(), is(AirplaneStatusFlag.LOAD));

        sc.setSchedule(new Schedule(null, AirplaneStatusFlag.TAKE_OFF, null, 0));

        assertThat(a.getState(), is(AirplaneStatusFlag.TAKE_OFF));
    }

}
