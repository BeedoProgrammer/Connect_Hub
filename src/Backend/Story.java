package Backend;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Story extends Content {
    
    private Long groupId = null;
    public Story(long contentId, long authorId, String contentString, String contentImagePath) {
        super(contentId, authorId, contentString, contentImagePath);

    }
    public Story(long contentId, long authorId, String contentString, String contentImagePath, Long groupId) {
        super(contentId, authorId, contentString, contentImagePath);

    }

    public Long getGroupId() {
        return groupId;
    }
    public boolean isDue() {
        if (this.getTimestamp().until(LocalDateTime.now(), ChronoUnit.HOURS) >= 24) {
            return true;
        }
        return false;
    }
}
