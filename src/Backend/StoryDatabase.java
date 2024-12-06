
package Backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class StoryDatabase extends Database {  
    private static StoryDatabase instance = null;
    
    public static StoryDatabase getInstance() throws IOException, FileNotFoundException, ParseException {
        if (instance == null) {
            instance = new StoryDatabase("stories.json");
        }
        instance.readFromFile();
        return instance;
    }

    private StoryDatabase(String fileName) {
        super(fileName);
    }
    @Override
    protected Story getRecordFromMap(Map<String,Object> mapOfStory) {
    long contentId = (long)mapOfStory.get("contentId");
    long authorId = (long)mapOfStory.get("authorId");
    String timestamp = (String)mapOfStory.get("timestamp");
    String contentString = (String)mapOfStory.get("contentString");
    String contentImagePath = (String)mapOfStory.get("contentImagePath");
    Story tempStory = new Story(contentId, authorId, contentString, contentImagePath);
    tempStory.setTimestamp(LocalDateTime.parse(timestamp));
    if (tempStory.isDue()) {
        return null;
    }
    return tempStory;
    }
    @Override
    protected Map<String,Object> getMapFromRecord(Object story) {
        Map<String,Object> tempStoryMap = new HashMap<>();
        Story tempStory = (Story)story;
        tempStoryMap.put("contentId", tempStory.getContentId());
        tempStoryMap.put("authorId", tempStory.getAuthorId());
        tempStoryMap.put("timestamp", tempStory.getTimestamp().toString());
        tempStoryMap.put("contentString", tempStory.getContentString());
        tempStoryMap.put("contentImagePath", tempStory.getContentImagePath());
        return tempStoryMap;
    }
    public void addStory(Story story) throws IOException, FileNotFoundException, ParseException {
        super.addRecord(story);
    }
    public ArrayList<Story> getStories() {
        ArrayList<Object> tempObjects = super.getRecords();
        ArrayList<Story> stories = new ArrayList<>();
        for (Object tempObject : tempObjects) {
            stories.add((Story)tempObject);
        }
        return stories;
    }
    public Story getStoryFromId(long storyId) {    // returns story object if found and null if not found
        ArrayList<Object> tempObjects = super.getRecords();
        for (int i = 0; i < tempObjects.size(); i++) {
            if (storyId == ((Story)tempObjects.get(i)).getContentId()) {
                return (Story)tempObjects.get(i);
            }
        }
        return null;
    }
}
