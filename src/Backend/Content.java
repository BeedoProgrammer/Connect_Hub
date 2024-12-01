package Backend;
//بسم الله الرحمن الرحيم\\
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public abstract class Content {
    private String contentId;
    private String authorId;
    private LocalDateTime timestamp;
    
    private String contentString;
    private BufferedImage contentImage;

    public Content(String contentId, String authorId, String contentString, BufferedImage contentImage) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.contentString = contentString;
        this.contentImage = contentImage;
        this.timestamp = LocalDateTime.now();
    }
    
    public void displayContent() {
        
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
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
