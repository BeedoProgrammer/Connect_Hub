package Backend;

import java.awt.image.BufferedImage;

public class Post extends Content {
    
    private Long groupId = null;
    public Post(long contentId, long authorId, String contentString, String contentImagePath) {
        super(contentId, authorId, contentString, contentImagePath);
    }
    public Post(long contentId, long authorId, String contentString, String contentImagePath, Long groupId) {
        super(contentId, authorId, contentString, contentImagePath);
        this.groupId = groupId;
    }
    public Long getGroupId() {
        return groupId;
    }
    
}
