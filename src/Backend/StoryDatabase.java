
package Backend;

import java.util.ArrayList;

public class StoryDatabase extends Database {
    private ArrayList<Story> stories;

    public StoryDatabase(String fileName) {
        super(fileName);
        stories = new ArrayList<>();
    }
    
}
