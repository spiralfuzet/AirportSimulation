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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class CsvAirplaneBuilderTest {

    @Test
    public void shouldCreateAirplaneFromCsvFile() throws AirplaneBuilderException {
        String content = ""
                + "#id;aliasName;aircraftType;fuelTankCapacity;actFuelLevel;maxSpeed;maxRange;State;actAltitude;Maintenance;maxPassangers;actPassangers;Weight;maxTakeoffWeight;maxLandingWeight\n"
                + "W62339;Wizz Air 62339;Airbus A320-232;1000;1000;904;5700;SERVICE;0;0;150;0;37230;77000;64500\n"
                + "AT702;Air Transar 702;Airbus A330-243;2500;2500;900;12499;SERVICE;0;0;253;0;168000;233000;180000";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        CsvAirplaneBuilder airplaneBuilder = new CsvAirplaneBuilder(inputStream);

        assertThat(airplaneBuilder.hasNext(), is(true));
        assertThat(airplaneBuilder.getNext().getId(), is("W62339"));

        assertThat(airplaneBuilder.hasNext(), is(true));
        assertThat(airplaneBuilder.getNext().getId(), is("AT702"));

        assertThat(airplaneBuilder.hasNext(), is(false));
    }

}
