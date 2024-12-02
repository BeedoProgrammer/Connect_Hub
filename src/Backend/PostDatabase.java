package Backend;

import java.util.ArrayList;

public class PostDatabase extends Database {
    private ArrayList<Post> posts;

    public PostDatabase(String fileName) {
        super(fileName);
        posts = new ArrayList<>();
    }
    
}
