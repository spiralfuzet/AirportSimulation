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

import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.event.StatusControllerSelector;
import java.util.LinkedList;
import java.util.Queue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author tothm
 */
public class ScheduleControllerTest {

    private static class SelectorAnswer implements Answer<Void> {

        public int invocationCounter = 0;

        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            invocationCounter++;
            return null;
        }
    }

    @Test
    public void shouldEmptyScheduleQueueAndCallEventSelector() throws Exception {

        Queue<Schedule> schQueue = new LinkedList<>();
        schQueue.add(new Schedule(null, AirplaneStatusFlag.LANDING, null, 0.2));
        schQueue.add(new Schedule(null, AirplaneStatusFlag.TAXI, null, 0.1));

        StatusControllerSelector mockSelector = Mockito.mock(StatusControllerSelector.class);
        SelectorAnswer selectorAnswer = new SelectorAnswer();
        Mockito.doAnswer(selectorAnswer).when(mockSelector).setSchedule((Schedule) Mockito.anyObject());

        ScheduleController sc = new ScheduleController(mockSelector, schQueue);

        assertThat(selectorAnswer.invocationCounter, is(0));
        assertThat(schQueue.size(), is(2));

        assertThat(sc.call(), is(StateFlag.RUNNING));
        assertThat(selectorAnswer.invocationCounter, is(1));
        assertThat(schQueue.size(), is(1));

        assertThat(sc.call(), is(StateFlag.RUNNING));
        assertThat(selectorAnswer.invocationCounter, is(2));
        assertThat(schQueue.size(), is(0));

        assertThat(sc.call(), is(StateFlag.ENDED));
        assertThat(selectorAnswer.invocationCounter, is(2));
        assertThat(schQueue.size(), is(0));
    }

}
