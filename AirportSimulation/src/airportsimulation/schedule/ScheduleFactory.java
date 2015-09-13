/*
 * Copyright (c) 2015 mdhtr.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    mdhtr - initial API and implementation and/or initial documentation
 */
package airportsimulation.schedule;

import airportsimulation.airplane.AirplaneStatusFlag;
import airportsimulation.utils.Factory;
import java.util.List;

/**
 *
 * @author mdhtr
 */
public class ScheduleFactory implements Factory<Schedule>{

    @Override
    public Schedule create(List<String> fields) {
    
        return new Schedule(fields.get(0), AirplaneStatusFlag.valueOf(fields.get(1)),
                fields.get(2), Integer.parseInt(fields.get(3)));}
    
}
