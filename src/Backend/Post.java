package Backend;

import java.awt.image.BufferedImage;

public class Post extends Content {
    
    public Post(long contentId, long authorId, String contentString, BufferedImage contentImage) {
        super(contentId, authorId, contentString, contentImage);
    }
    
}
