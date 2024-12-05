package Backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class PostDatabase extends Database {
//    private ArrayList<Post> posts;
    
    private static PostDatabase instance = null;
    
    public static PostDatabase getInstance() {
        if (instance == null) {
            instance = new PostDatabase("posts.json");
        }
        return instance;
    }

    private PostDatabase(String fileName) {
        super(fileName);
//        posts = new ArrayList<>();
    }

//    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies posts arraylist
//        this.posts.clear(); // remove all previous elements from list
//        JSONArray jsonPosts = new JSONArray();
//        jsonPosts = (JSONArray) new JSONParser().parse(new FileReader(getFileName()));
//        // parse each element in the jsonArray into a Post object
//        for (int i = 0; i < jsonPosts.size(); i++) {
//            posts.add(getPostFromMap((Map)jsonPosts.get(i)));
//        }
//    }
    @Override
    protected Post getRecordFromMap(Map<String,Object> mapOfPost) {
        long contentId = (long)mapOfPost.get("contentId");
        long authorId = (long)mapOfPost.get("authorId");
        String timestamp = (String)mapOfPost.get("timestamp");
        String contentString = (String)mapOfPost.get("contentString");
        String contentImagePath = (String)mapOfPost.get("contentImagePath");
        Post tempPost = new Post(contentId, authorId, contentString, contentImagePath);
        tempPost.setTimestamp(LocalDateTime.parse(timestamp));
        return tempPost;
    }
//    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves posts arraylist into json format file
//        JSONArray jsonPosts = new JSONArray();
//        for (int i = 0; i < posts.size(); i++) {
//            jsonPosts.add(getMapFromPost(posts.get(i)));
//        }
//        PrintWriter pw = new PrintWriter(getFileName()); 
//        pw.write(jsonPosts.toJSONString()); 
//        pw.flush(); 
//        pw.close(); 
//    }
    protected Map<String,Object> getMapFromRecord(Object post) {
        Map<String,Object> tempPostMap = new HashMap<>();
        Post tempPost = (Post)post;
        tempPostMap.put("contentId", tempPost.getContentId());
        tempPostMap.put("authorId", tempPost.getAuthorId());
        tempPostMap.put("timestamp", tempPost.getTimestamp().toString());
        tempPostMap.put("contentString", tempPost.getContentString());
        tempPostMap.put("contentImagePath", tempPost.getContentImagePath());
        return tempPostMap;
    }
    public void addPost(Post post) throws IOException, FileNotFoundException, ParseException {
        super.addRecord(post);
    }
    public ArrayList<Post> getPosts() {
        ArrayList<Object> tempObjects = super.getRecords();
        ArrayList<Post> posts = new ArrayList<>();
        for (Object tempObject : tempObjects) {
            posts.add((Post)tempObject);
        }
        return posts;
    }
    public Post getPostFromId(long postId) {    // returns post object if found and null if not found
        ArrayList<Object> tempObjects = super.getRecords();
        for (int i = 0; i < tempObjects.size(); i++) {
            if (postId == ((Post)tempObjects.get(i)).getContentId()) {
                return (Post)tempObjects.get(i);
            }
        }
        return null;
    }

}
