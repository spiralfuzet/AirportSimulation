package airportsimulation;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dobos
 */
public class CsvAirportBuilderTest {
    
    public CsvAirportBuilderTest() {
    }

    @Test
    public void shouldBuildAirportFromCsvMap() {
        
        AirportBuilder builder = 
                new CsvAirportBuilder(createAirportMap());
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(false));
        assertThat(builder.getNext(), is(nullValue()));
    }
    
    private Map<String, Map> createAirportMap() {
        String testCsv = "id;aliasName;Country;maxCapacityAircrafts;actCapacityAircrafts;maxCapacityPassangers;countRunways\n" +
                        "BUD;BUD/LHBP Budapest Liszt Ferenc International Airport;Hungary;75;0;4000;2\n" +
                        "YUL;YUL/CYUL Montreal Pierre Elliott Trudeau Airport;Canada;50;0;2500;2";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ";");
        
        return parser.getMap();
    }
    
    @Test
    public void shouldBuildAirportFromCsvList() {
        
        AirportBuilder builder = 
                new CsvAirportBuilder(createAirportList());
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(false));
        assertThat(builder.getNext(), is(nullValue()));
    }
    
    private List<String[]> createAirportList() {
        String testCsv = "id;aliasName;Country;maxCapacityAircrafts;actCapacityAircrafts;maxCapacityPassangers;countRunways\n" +
                        "BUD;BUD/LHBP Budapest Liszt Ferenc International Airport;Hungary;75;0;4000;2\n" +
                        "YUL;YUL/CYUL Montreal Pierre Elliott Trudeau Airport;Canada;50;0;2500;2";
        StringReader reader = new StringReader(testCsv);
        CSVParser parser = new CSVParser(new BufferedReader(reader), true, ";");
        
        return parser.getList();
    }
}
