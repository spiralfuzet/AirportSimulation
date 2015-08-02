/*
 * Copyright (c) 2015 home01.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    home01 - initial API and implementation and/or initial documentation
 */
package airportsimulation;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author home01
 */
public class CsvAirplaneBuilderTest {
    
    public CsvAirplaneBuilderTest() {
    }

    @Test
    public void shouldBuildExpressionFromCsvMap() {
        
        AirplaneBuilder builder = 
                new CsvAirplaneBuilder(createAirplaneMap());
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(false));
        assertThat(builder.getNext(), is(nullValue()));
    }
    
    private Map<String, Map> createAirplaneMap() {
        String testCsv = "id;aliasName;aircraftType;fuelTankCapacity;actFuelLevel;maxSpeed;maxRange;State;actAltitude;Maintenance;maxPassangers;actPassangers;Weight;maxTakeoffWeight;maxLandingWeight\n" +
                        "W62339;Wizz Air 62339;Airbus A320-232;1000;1000;904;5700;0;0;0;150;0;37230;77000;64500\n" +
                        "AT702;Air Transar 702;Airbus A330-243;2500;2500;900;12499;0;0;0;253;0;168000;233000;180000";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ";");
        
        return parser.getMap();
    }
    
    @Test
    public void shouldBuildExpressionFromCsvList() {
        
        AirplaneBuilder builder = 
                new CsvAirplaneBuilder(createAirplaneList());
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(true));
        Airplane airplane = builder.getNext();
        assertThat(airplane, is(not(nullValue())));
        assertThat(airplane.getActFuelLevel(), is(1000.0));
        
        assertThat(builder.hasNext(), is(false));
        assertThat(builder.getNext(), is(nullValue()));
    }
    
    private List<String[]> createAirplaneList() {
        String testCsv = "id;aliasName;aircraftType;fuelTankCapacity;actFuelLevel;maxSpeed;maxRange;State;actAltitude;Maintenance;maxPassangers;actPassangers;Weight;maxTakeoffWeight;maxLandingWeight\n" +
                        "W62339;Wizz Air 62339;Airbus A320-232;1000;1000;904;5700;0;0;0;150;0;37230;77000;64500\n" +
                        "AT702;Air Transar 702;Airbus A330-243;2500;2500;900;12499;0;0;0;253;0;168000;233000;180000";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ";");
        
        return parser.getList();
    }
    
}
