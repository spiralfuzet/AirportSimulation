
package airportsimulation;

import java.io.StringReader;
import java.util.List;
import javax.json.JsonObject;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmate
 */
public class JsonAirplaneReaderTest {

    @Test
    public void shouldReadJsonStream() {        
        String contentOfJson = "{\"Airplanes\":\n[\n"
                + "{\"Airplane\" :{\"fuelTankCapacity\": \"1000.0\"}},\n"
                + "{\"Airplane\" :{\"fuelTankCapacity\": \"1500.0\"}}"
                + "\n]\n"
                + "}";
        
        StringReader reader = new StringReader(contentOfJson);
                
        AirplaneReader airplaneReader = new JsonAirplaneReader(reader);
        
        List<JsonObject> objecList = airplaneReader.getAllAirplanes();
        
        assertThat(objecList.size(), is(2));
        
        
    }
    
}
