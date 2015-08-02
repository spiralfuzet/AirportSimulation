
package airportsimulation;

import java.util.ArrayList;
import java.util.List;
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
 * @author tmate
 */
public class JsonAirplaneBuilderTest {
    
    @Test
    public void shouldBuildExpressionFromJsonFile() {
        
        AirplaneBuilder builder = 
                new JsonAirplaneBuilder(createListOfAirplaneObject());
        
        assertThat(builder.hasNext(), is(true));
        assertThat(builder.getNext(), is(not(nullValue())));
        
        assertThat(builder.hasNext(), is(false));
        assertThat(builder.getNext(), is(nullValue()));
    }
    
    
    
    private JsonObject createAirplaneObject(Double fuelCapacity) {
        
        JsonObjectBuilder airplaneBuilder = Json.createObjectBuilder();
        JsonObjectBuilder airplaneObjectBuilder = Json.createObjectBuilder();
        
        
        airplaneObjectBuilder.add("actFuelLevel", fuelCapacity.toString());
        
        airplaneBuilder.add("Airplane", airplaneObjectBuilder);
        
        JsonObject obj = airplaneBuilder.build();
        
        return obj;
    }
    
    private List<JsonObject> createListOfAirplaneObject() {
        
        List<JsonObject> objectList = new ArrayList<>();
        
        objectList.add(createAirplaneObject(1000.0));
        objectList.add(createAirplaneObject(1500.0));
        
        return objectList;
    }
    
}
