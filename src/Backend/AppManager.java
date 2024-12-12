/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Frontend.*;
import NewsFeed.*;

public class AppManager {
    
//    User currentUser;
//    String currentState;
//    
//    public AppManager() {
//        currentState = "Login";
//    }

    public static void main(String args[]) {
        ConnectHub connectHub = new ConnectHub("Connect Hub");
        connectHub.setLocationRelativeTo(null);
        connectHub.setVisible(true);
    }
    
    
//    private void login(){
//        this.currentUser = null;
//        ConnectHub connectHub = new ConnectHub("Connect Hub");
//        connectHub.setLocationRelativeTo(null);
//        connectHub.setVisible(true);
//    }
}
