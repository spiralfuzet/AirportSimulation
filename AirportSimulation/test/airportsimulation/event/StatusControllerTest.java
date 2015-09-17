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
import airportsimulation.schedule.Schedule;
import airportsimulation.schedule.StateFlag;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author tothm
 */
public class StatusControllerTest {

    @Test
    public void shouldChangeStatus() throws EventHandlerException, InterruptedException, ExecutionException {

        Airplane a = new Airplane();
        a.setState(AirplaneStatusFlag.LOAD);

        Future<StateFlag> mockFuture = Mockito.mock(Future.class);
        Mockito.when(mockFuture.get()).thenReturn(StateFlag.ENDED);

        ExecutorService mockExecutorService = Mockito.mock(ExecutorService.class);
        Mockito.when(
                mockExecutorService.submit((Callable<StateFlag>)Mockito.anyObject()))
                .thenReturn(mockFuture);

        StatusControllerSelector sc = new StatusControllerSelector(a, mockExecutorService);

        assertThat(a.getState(), is(AirplaneStatusFlag.LOAD));

        sc.setSchedule(new Schedule(null, AirplaneStatusFlag.TAKE_OFF, null, 0));

        assertThat(a.getState(), is(AirplaneStatusFlag.TAKE_OFF));
    }

}
