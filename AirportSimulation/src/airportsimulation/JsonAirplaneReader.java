
package airportsimulation;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import javax.json.*;
import javax.json.stream.JsonParsingException;

/**
 *
 * @author tmate
 */
public class JsonAirplaneReader implements AirplaneReader {
    
    private Reader reader = null;
    
    public JsonAirplaneReader(String inputFileName) {

        try {
            this.reader = new FileReader(inputFileName);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    public JsonAirplaneReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<Airplane> getAllAirplanes() {

        List<Airplane> airplaneList = new ArrayList<>();
        
        try (JsonReader rdr = Json.createReader(reader)) {

            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("Airplanes");

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                Airplane a = new Airplane();
                airplaneList.add(a);
            }

            rdr.close();
        }
        catch (JsonParsingException ex) {
            System.out.println(ex);
        }
        
        return airplaneList;
    }
    
}
