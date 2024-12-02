
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
    private ArrayList<Story> stories;

    public StoryDatabase(String fileName) {
        super(fileName);
        stories = new ArrayList<>();
    }
    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies stories arraylist
        this.stories.clear(); // remove all previous elements from list
        JSONArray jsonStories = new JSONArray();
        jsonStories = (JSONArray) new JSONParser().parse(new FileReader(getFileName()));
        // parse each element in the jsonArray into a story object
        for (int i = 0; i < jsonStories.size(); i++) {
            stories.add(getStoryFromMap((Map)jsonStories.get(i)));
        }
    }
    private Story getStoryFromMap(Map<String,String> mapOfStory) {
        String contentIdString = mapOfStory.get("contentId");
        String authorIdString = mapOfStory.get("authorId");
        String timestampString = mapOfStory.get("timestamp");
        String contentStringString = mapOfStory.get("contentString");
        String contentImagePathString = mapOfStory.get("contentImagePath");
        Story tempStory = new Story(Long.parseLong(contentIdString), Long.parseLong(authorIdString), contentIdString, contentImagePathString);
        tempStory.setTimestamp(LocalDateTime.parse(timestampString));
        return tempStory;
    }
    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves stories arraylist into json format file
        JSONArray jsonStories = new JSONArray();
        for (int i = 0; i < stories.size(); i++) {
            jsonStories.add(getMapFromStory(stories.get(i)));
        }
        PrintWriter pw = new PrintWriter(getFileName()); 
        pw.write(jsonStories.toJSONString()); 
        pw.flush(); 
        pw.close(); 
    }
    private Map<String,String> getMapFromStory(Story story) {
        Map<String,String> tempStoryMap = new HashMap<>();
        tempStoryMap.put("contentId", String.valueOf(story.getContentId()));
        tempStoryMap.put("authorId", String.valueOf(story.getAuthorId()));
        tempStoryMap.put("timestamp", story.getTimestamp().toString());
        tempStoryMap.put("contentString", story.getContentString());
        tempStoryMap.put("contentImagePath", story.getContentImagePath());
        return tempStoryMap;
    }
    public void addStroy(Story story) throws IOException, FileNotFoundException, ParseException {
        stories.add(story);
        saveToFile();
    }
    public ArrayList<Story> getStories() {
        return stories;
    }
}
