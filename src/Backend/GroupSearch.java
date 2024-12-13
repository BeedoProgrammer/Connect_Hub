/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Groups.Group;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class GroupSearch {
      private User CurrentUser;
  private  ArrayList <Group> SystemGroups; ///// all groups in system
   private   ArrayList<Group> MyDesiredGroups;  ///// groups with this search
    public GroupSearch (User CurrentUser ,ArrayList<Group> SystemGroups)
    {
        this.CurrentUser=CurrentUser;
        this.SystemGroups=SystemGroups;
        MyDesiredGroups=new ArrayList<>();
    }
    public void DesiredGroups(String searchName) {
    
        for (Group group : SystemGroups) {
            if (group.getName().toLowerCase().contains(searchName.toLowerCase())) {
                MyDesiredGroups.add(group);
            }
        }
  }  
     public ArrayList<Group> joinedGroups() {
    ArrayList<Group> joinedGroups = new ArrayList<>();
    for (Group group : MyDesiredGroups) {
        if (CurrentUser.getMygroups().contains(group)) {
            joinedGroups.add(group);
        }
    }
    return joinedGroups;
}
public ArrayList<Group> unjoinedGroups() {
    ArrayList<Group> unjoinedGroups = new ArrayList<>();
    for (Group group : MyDesiredGroups) {
        if (!CurrentUser.getMygroups().contains(group)) {
            unjoinedGroups.add(group);
        }
    }
    return unjoinedGroups;
}

}
