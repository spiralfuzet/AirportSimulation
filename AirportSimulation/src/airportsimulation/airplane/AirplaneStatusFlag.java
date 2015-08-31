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
package airportsimulation.airplane;

/**
 *
 * @author tothm
 */
public enum AirplaneStatusFlag {
    IN_FLIGHT(1),
    LANDING(1<<1),
    TAKE_OFF(1<<2),
    MAINTENANCE(1<<3),
    LOAD(1<<4),
    UNLOAD(1<<5),
    TAXI(1<<6);

    public final int flagValue;

    private AirplaneStatusFlag(final int flagValue) {
        this.flagValue = flagValue;
    }

    public int getFlagValue() {
        return flagValue;
    }
};
