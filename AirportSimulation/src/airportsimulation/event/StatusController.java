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

import airportsimulation.event.handler.EventHandler;
import airportsimulation.schedule.StateFlag;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author tothm
 */
public class StatusController {

    private final ExecutorService executorService;
    private Future<StateFlag> statusEvent;

    public StatusController(ExecutorService executorService) {
        this.executorService = executorService;
        this.statusEvent = null;
    }

    public void startEvent(EventHandler eventHandler) {
        while ((statusEvent = executorService.submit(eventHandler)) != null) {
            try {
                StateFlag stateFlag = statusEvent.get();
                if (StateFlag.ENDED.equals(stateFlag)) {
                    break;
                }
                statusEvent.cancel(true);
            } catch (ExecutionException | InterruptedException ex) {
                System.err.println("Cannot get state controller future due to:\n" + ex);
            }
        }
    }
}
