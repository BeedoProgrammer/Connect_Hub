package Backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Database {
    private final String fileName;
    private ArrayList<Object> records;

    public Database(String fileName) {
        this.fileName = fileName;
        records = new ArrayList<>();
    }
    
    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies users arraylist
        records.clear(); // remove all previous elements from list
        JSONArray jsonRecords = new JSONArray();
        jsonRecords = (JSONArray) new JSONParser().parse(new FileReader(fileName));
        // parse each element in the jsonArray into a user object
        for (int i = 0; i < jsonRecords.size(); i++) {
            records.add(getRecordFromMap((Map)jsonRecords.get(i)));
        }
    }
    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves users arraylist into json format file
        JSONArray jsonRecords = new JSONArray();
        for (int i = 0; i < records.size(); i++) {
            jsonRecords.add(getMapFromRecord(records.get(i)));
        }
        PrintWriter pw = new PrintWriter(fileName); 
        pw.write(jsonRecords.toJSONString()); 
        pw.flush(); 
        pw.close(); 
    }
    public void addRecord(Object record) throws IOException, FileNotFoundException, ParseException {
        records.add(record);
//        saveToFile();
    }
    public ArrayList<Object> getRecords() {
        return records;
    }
    protected abstract Map<String,Object> getMapFromRecord(Object record);
    protected abstract Object getRecordFromMap(Map<String,Object> mapOfRecord);

    public String getFileName() {
        return fileName;
    }

//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
    
}
