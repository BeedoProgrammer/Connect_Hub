package Backend;

import java.util.*;

public class GroupManagement {
    private long groupID;
    private String name;
    private String groupPhoto;
    private String describtion;
    private HashMap<Long, GroupDetails> users;

    public GroupManagement(long groupID, String name, String groupPhoto, String describtion) {
        this.groupID = groupID;
        this.name = name;
        this.groupPhoto = groupPhoto;
        this.describtion = describtion;
    }

    public long getGroupID() {
        return groupID;
    }

    public String getName() {
        return name;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setName(String name, User user, long GroupID) {
        this.name = name;
    }

    public void setGroupPhoto(String groupPhoto, User user) {
        this.groupPhoto = groupPhoto;
    }

    public void setDescribtion(String describtion, User user) {
        this.describtion = describtion;
    }
}
