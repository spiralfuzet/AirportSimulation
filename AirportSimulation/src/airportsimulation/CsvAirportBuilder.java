package airportsimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dobos
 */
public class CsvAirportBuilder implements AirportBuilder{
    private boolean hasNext;
    private List<Airport> airports = new ArrayList<>();
    private int airportCountAct = -1;
    private final int maxCapacityPassangers = 3;
            
    public CsvAirportBuilder(Map<String, Map> airportsData)  {
        airports = new ArrayList<>();
        
        for (String airport : airportsData.keySet()) {
            airports.add(new Airport(Double.parseDouble(airportsData.get(airport).get("maxCapacityPassangers").toString())));
            airportCountAct += 1;
        }
        if (airportCountAct != -1) {
            hasNext = true;
        }
    }
    
    public CsvAirportBuilder(List<String[]> airportsData)  {
        airports = new ArrayList<>();
        
        
        for (String[] airport : airportsData) {
            airports.add(new Airport(Double.parseDouble(airport[maxCapacityPassangers])));
            airportCountAct += 1;
        }
        if (airportCountAct != -1) {
            hasNext = true;
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Airport getNext() {
        if (hasNext == false) {
            return null;
        } else {
            if (airportCountAct != -1) {
                Airport airportAct = airports.get(airportCountAct);
                airportCountAct -= 1;
                
                if (airportCountAct == -1) {
                    hasNext = false;
                }
                return airportAct;
            }
        }
        return null;
    }
}
