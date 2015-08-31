/*
 * Copyright (c) 2015 Rendszergazda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rendszergazda - initial API and implementation and/or initial documentation
 */
package airportsimulation;

import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.utils.Observable;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Rendszergazda
 */
public class StatusControllerTest {

    private static class MockObservable implements Observable {

        boolean called = false;

        @Override
        public void update() {
            called = true;
        }

    }

    @Test
    public void shouldControllPlanes() {
        StatusController controller = new StatusController();
        MockObservable mockObservable = new MockObservable();
        controller.attach(mockObservable);

        controller.setState(AirplaneStatusFlag.TAXI);

        assertThat(mockObservable.called, is(true));

    }

}
