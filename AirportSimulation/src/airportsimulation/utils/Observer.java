/*
 * Copyright (c) 2015 Rendszergazda.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */
package airportsimulation.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tothm
 */
public class Observer {
    List<Observable> observables;

    public Observer() {
        observables = new ArrayList<>();
    }
    
    public void attach(Observable observable) {
        observables.add(observable);
    }
    
    public void notify_all() {
        for (Observable o : observables) {
            o.update();
        }
    }
   
}
