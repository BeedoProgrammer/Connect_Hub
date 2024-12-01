package Backend;

public class ProfileManagement {
    private String profilePhoto;
    private String coverPhoto;
    private String bio; 
    private User userDetails;

    public ProfileManagement(String profilePhoto, String coverPhoto, String bio, User userDetails) {
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.bio = bio;
        this.userDetails = userDetails;
    }
    
    public void getContent(){
        //
    } 
   public void getListOfFriends(){
        //
    }
}
