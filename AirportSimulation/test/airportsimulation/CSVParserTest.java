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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author home01
 */
public class CSVParserTest {
    
    public CSVParserTest() {
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void shouldThrowFileNotFoundException() {
        String wrongFilename = "test_file_not_available.csv";
        boolean hasHeader = true;
        CSVParser parser = new CSVParser(wrongFilename, hasHeader, ";");        
    }
    
    @Test
    public void shouldReadCSVWithHeader() {
        String testCsv = "id;aliasName;Country;maxCapacityAircrafts\n" +
                        "BUD;Ferihegy;Hungary;75\n" +
                        "YUL;Montreal;Canada;50";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ";");
        
        Map<String, Map> csvMap = parser.getMap();
        assertThat(csvMap.size(), is(2));
        assertThat(csvMap.get("BUD").size(), is(3));
        
        List<String[]> csvList = parser.getList();
        assertThat(csvList.size(), is(2));
        assertThat(csvList.get(0).length, is(4));
        
    }
    
    @Test
    public void shouldReadCSVWithHeaderWithNotComplyingDelimiter() {
        String testCsv = "id;aliasName;Country;maxCapacityAircrafts\n" +
                        "BUD;Ferihegy;Hungary;75\n" +
                        "YUL;Montreal;Canada;50";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ",");
        
        Map<String, Map> csvMap = parser.getMap();
        assertThat(csvMap.size(), is(2));
        for (Map csvInnerMap : csvMap.values()){
            assertThat(csvInnerMap.size(), is(0));
        }
        
        List<String[]> csvList = parser.getList();
        assertThat(csvList.size(), is(2));
        assertThat(csvList.get(0).length, is(1));
        
    }
    
    @Test
    public void shouldReadCSVWithOutHeader() {
        String testCsv = "id;aliasName;Country;maxCapacityAircrafts\n" +
                        "BUD;Ferihegy;Hungary;75\n" +
                        "YUL;Montreal;Canada;50";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), false, ";");
        
        Map<String, Map> csvMap = parser.getMap();
        assertThat(csvMap.size(), is(0));

        List<String[]> csvList = parser.getList();
        assertThat(csvList.size(), is(3));
        assertThat(csvList.get(0).length, is(4));
    }
    
}
