package Backend;
//بسم الله الرحمن الرحيم\\
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public abstract class Content {
    private long contentId;
    private long authorId;
    private LocalDateTime timestamp;
    
    private String contentString;
    private BufferedImage contentImage;

    public Content(long contentId, long authorId, String contentString, BufferedImage contentImage) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.contentString = contentString;
        this.contentImage = contentImage;
        this.timestamp = LocalDateTime.now();
    }
    
    public void displayContent() {
        
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public BufferedImage getContentImage() {
        return contentImage;
    }

    public void setContentImage(BufferedImage contentImage) {
        this.contentImage = contentImage;
    }
    
}
