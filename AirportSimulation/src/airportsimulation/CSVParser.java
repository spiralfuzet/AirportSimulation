package airportsimulation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dobos
 */
public final class CSVParser {
    private String fileName = "";
    private BufferedReader fileReader = null;
    private String delimiter;
    private final boolean hasHeader;
    private Map<String, Map> dataMap = new HashMap<>();
    private List<String[]> dataList = new ArrayList<>();
    
    public CSVParser(String _fileName, boolean _hasHeader, String _delimiter) throws FileNotFoundException {
        fileName = _fileName;
        hasHeader = _hasHeader;
        delimiter = _delimiter;
        try
        {   
            fileReader = new BufferedReader(new FileReader(fileName));
            this.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR CSV file not found: " + fileName);
            throw e;
        }
    }
    
    public CSVParser(BufferedReader reader, boolean _hasHeader, String _delimiter) {
        fileReader = reader;
        hasHeader = _hasHeader;
        delimiter = _delimiter;
        this.readFile();
    }
    
    public void readFile() {

        String line = "";
        String header = "";
       
        try
        {   
            if (hasHeader) {
                header = fileReader.readLine();
            }
            
            while ((line = fileReader.readLine()) != null)
            {
                String[] records = line.split(delimiter);

                addToList(records);
                if (hasHeader) {
                    addToMap(header, records);
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR while reading CSV File: " + fileName);
        }
        finally
        {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("ERROR while closing CSVParser");
                }
            }
        }
    }
    
    private void addToList(String[] data) {
        dataList.add(data);
    }
    
    public List<String[]> getList() {
        return dataList;        
    }
    
    private void addToMap(String header, String[] data) {
        Map<String, String> innerMap = new HashMap<String, String>();
        String[] headers = header.split(delimiter);
        
        for (int k = 1; k<headers.length; k++) {
            innerMap.put(headers[k], data[k]);
        }
        dataMap.put(data[0], innerMap);
    }
    
    public Map getMap() {
        return dataMap;        
    }
    

}
