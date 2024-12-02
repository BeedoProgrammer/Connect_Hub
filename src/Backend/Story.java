package Backend;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Story extends Content {
    
    public Story(long contentId, long authorId, String contentString, BufferedImage contentImage) {
        super(contentId, authorId, contentString, contentImage);
    }
    public boolean isDue() {
        if (this.getTimestamp().until(LocalDateTime.now(), ChronoUnit.HOURS) >= 24) {
            return true;
        }
        return false;
    }
}
