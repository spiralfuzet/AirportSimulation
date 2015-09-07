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
package airportsimulation.airport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author tothm
 */
public class CsvAirportBuilderTest {

    @Test
    public void shouldBuildAirports() throws AirportBuilderException {
        String content = ""
                + "#id;aliasName;Country;maxCapacityAircrafts;actCapacityAircrafts;maxCapacityPassangers;countRunways\n"
                + "BUD;BUD/LHBP Budapest Liszt Ferenc International Airport;Hungary;75;0;4000;2\n"
                + "YUL;YUL/CYUL Montreal Pierre Elliott Trudeau Airport;Canada;50;0;2500;2";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        AirportBuilder airportBuilder = new CsvAirportBuilder(inputStream);

        assertThat(airportBuilder.hasNext(), is(true));
        assertThat(airportBuilder.getNext().getAliasName(), is("BUD/LHBP Budapest Liszt Ferenc International Airport"));

        assertThat(airportBuilder.hasNext(), is (true));
        assertThat(airportBuilder.getNext().getAliasName(), is("YUL/CYUL Montreal Pierre Elliott Trudeau Airport"));

        assertThat(airportBuilder.hasNext(), is (false));
    }

}
