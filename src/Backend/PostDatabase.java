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
    private Post getPostFromMap(Map<String,String> mapOfPost) {
        String contentIdString = mapOfPost.get("contentId");
        String authorIdString = mapOfPost.get("authorId");
        String timestampString = mapOfPost.get("timestamp");
        String contentStringString = mapOfPost.get("contentString");
        String contentImagePathString = mapOfPost.get("contentImagePath");
        Post tempPost = new Post(Long.parseLong(contentIdString), Long.parseLong(authorIdString), contentIdString, contentImagePathString);
        tempPost.setTimestamp(LocalDateTime.parse(timestampString));
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
    private Map<String,String> getMapFromPost(Post post) {
        Map<String,String> tempStoryMap = new HashMap<>();
        tempStoryMap.put("contentId", String.valueOf(post.getContentId()));
        tempStoryMap.put("authorId", String.valueOf(post.getAuthorId()));
        tempStoryMap.put("timestamp", post.getTimestamp().toString());
        tempStoryMap.put("contentString", post.getContentString());
        tempStoryMap.put("contentImagePath", post.getContentImagePath());
        return tempStoryMap;
    }
    public void addPost(Post post) throws IOException, FileNotFoundException, ParseException {
        posts.add(post);
        saveToFile();
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }

}
