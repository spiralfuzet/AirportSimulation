
package airportsimulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.json.JsonObject;

/**
 *
 * @author tmate
 */
public class JsonAirplaneBuilder implements AirplaneBuilder {

    private final Queue<Airplane> airplanes;
    private boolean hasNext;
    
    public JsonAirplaneBuilder(List<JsonObject> airplanesData) {
        airplanes = new LinkedList<>();
        
        for (JsonObject airplaneData : airplanesData) {
            String sActFuelCap = airplaneData.getJsonObject("Airplane").getString("actFuelLevel");
            Double fuelLevel = Double.parseDouble(sActFuelCap);
            airplanes.add(new Airplane(fuelLevel));
        }
        
        if (!airplanes.isEmpty()) {
            hasNext = true;
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Airplane getNext() {
        if (hasNext == false) {
            return null;
        }
        
        hasNext = false;
        return airplanes.remove();
    }
}
