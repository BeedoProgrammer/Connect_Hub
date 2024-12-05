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
    private ArrayList<Post> posts;

    public PostDatabase(String fileName) {
        super(fileName);
        posts = new ArrayList<>();
    }

    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies posts arraylist
        this.posts.clear(); // remove all previous elements from list
        JSONArray jsonPosts = new JSONArray();
        jsonPosts = (JSONArray) new JSONParser().parse(new FileReader(getFileName()));
        // parse each element in the jsonArray into a Post object
        for (int i = 0; i < jsonPosts.size(); i++) {
            posts.add(getPostFromMap((Map)jsonPosts.get(i)));
        }
    }
    private Post getPostFromMap(Map<String,Object> mapOfPost) {
        long contentId = (long)mapOfPost.get("contentId");
        long authorId = (long)mapOfPost.get("authorId");
        String timestamp = (String)mapOfPost.get("timestamp");
        String contentString = (String)mapOfPost.get("contentString");
        String contentImagePath = (String)mapOfPost.get("contentImagePath");
        Post tempPost = new Post(contentId, authorId, contentString, contentImagePath);
        tempPost.setTimestamp(LocalDateTime.parse(timestamp));
        return tempPost;
    }
    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves posts arraylist into json format file
        JSONArray jsonPosts = new JSONArray();
        for (int i = 0; i < posts.size(); i++) {
            jsonPosts.add(getMapFromPost(posts.get(i)));
        }
        PrintWriter pw = new PrintWriter(getFileName()); 
        pw.write(jsonPosts.toJSONString()); 
        pw.flush(); 
        pw.close(); 
    }
    private Map<String,Object> getMapFromPost(Post post) {
        Map<String,Object> tempPostMap = new HashMap<>();
        tempPostMap.put("contentId", post.getContentId());
        tempPostMap.put("authorId", post.getAuthorId());
        tempPostMap.put("timestamp", post.getTimestamp().toString());
        tempPostMap.put("contentString", post.getContentString());
        tempPostMap.put("contentImagePath", post.getContentImagePath());
        return tempPostMap;
    }
    public void addPost(Post post) throws IOException, FileNotFoundException, ParseException {
        posts.add(post);
        saveToFile();
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }

}
