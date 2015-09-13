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
package airportsimulation.airplane;

import airportsimulation.utils.Factory;
import java.util.List;

/**
 *
 * @author mdhtr
 */
public class AirplaneFactory implements Factory<Airplane> {

    @Override
    public Airplane create(List<String> fields) throws NumberFormatException {
        Airplane airplane = new Airplane(fields.get(0), fields.get(1), fields.get(2),
                Double.parseDouble(fields.get(3)), Double.parseDouble(fields.get(4)),
                Double.parseDouble(fields.get(5)), Double.parseDouble(fields.get(6)),
                AirplaneStatusFlag.valueOf(fields.get(7)), Double.parseDouble(fields.get(8)),
                Integer.parseInt(fields.get(9)), Integer.parseInt(fields.get(10)),
                Integer.parseInt(fields.get(11)), Integer.parseInt(fields.get(12)),
                Integer.parseInt(fields.get(13)), Integer.parseInt(fields.get(14)));
        return airplane;
    }

}
