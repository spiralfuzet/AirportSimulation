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
public class CSVParser {
    private final String fileName;
    private String delimiter = ";";
    private final boolean hasHeader;
    private Map<String, Map> dataMap = new HashMap<String, Map>();
    private List<String[]> dataList = new ArrayList<String[]>();
    
    public CSVParser(String _fileName, boolean _hasHeader) {
        fileName = _fileName;
        hasHeader = _hasHeader;
        this.readFile();
    }
    
    public CSVParser(String _fileName, boolean _hasHeader, String _delimiter) {
        fileName = _fileName;
        hasHeader = _hasHeader;
        delimiter = _delimiter;
        this.readFile();
    }
    
    public void setDelimiter (String _delimiter) {
        delimiter = _delimiter;
    }
    public void readFile() {
        
        BufferedReader fileReader = null;
        String line = "";
        String header = "";
       
        try
        {   
            fileReader = new BufferedReader(new FileReader(fileName));
            
            if (hasHeader) {
                header = fileReader.readLine();
            }
            
            while ((line = fileReader.readLine()) != null)
            {
                String[] records = line.split(delimiter);

                addToList(records);
                addToMap(header, records);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR CSV file not found: " + fileName);
        }
        catch (Exception e) {
            System.out.println("ERROR while reading CSV File: " + fileName);
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("ERROR while closing CSVParser");
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
        Map<String, String> smallmap = new HashMap<String, String>();
        String[] headers = header.split(delimiter);
        
        for (int k = 1; k<headers.length; k++) {
            smallmap.put(headers[k], data[k]);
        }
        dataMap.put(data[0], smallmap);
    }
    
    public Map getMap() {
        return dataMap;        
    }
    

}
