package Backend;

import java.awt.image.BufferedImage;

public class Post extends Content {
    
    public Post(String contentId, String authorId, String contentString, BufferedImage contentImage) {
        super(contentId, authorId, contentString, contentImage);
    }
    
}
