package airportsimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dobos
 */
public class CsvAirplaneBuilder implements AirplaneBuilder{
    private boolean hasNext;
    private List<Airplane> airplanes = new ArrayList<>();
    private int airplaneCountAct = -1;
    
    private final int idxID = 3;
    private final int idxFuelTankCapacity = 3;
            
    public CsvAirplaneBuilder(Map<String, Map> airplanesData)  {
        airplanes = new ArrayList<>();
        
        for (String airplane : airplanesData.keySet()) {
            double fuelTankCapacity = Double.parseDouble(airplanesData.get(airplane).get("fuelTankCapacity").toString());
            airplanes.add(new Airplane(airplane, fuelTankCapacity));
            airplaneCountAct += 1;
        }
        if (airplaneCountAct != -1) {
            hasNext = true;
        }
    }
    
    public CsvAirplaneBuilder(List<String[]> airplanesData)  {
        airplanes = new ArrayList<>();

        for (String[] airplane : airplanesData) {
            String id = airplane[idxID];
            double fuelTankCapacity = Double.parseDouble(airplane[idxFuelTankCapacity]);
            airplanes.add(new Airplane(id, fuelTankCapacity));
            airplaneCountAct += 1;
        }
        if (airplaneCountAct != -1) {
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
        } else {
            if (airplaneCountAct != -1) {
                Airplane airplaneAct = airplanes.get(airplaneCountAct);
                airplaneCountAct -= 1;
                
                if (airplaneCountAct == -1) {
                    hasNext = false;
                }
                return airplaneAct;
            }
        }
        return null;
    }
    
}
