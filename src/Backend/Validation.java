package Backend;

import java.util.regex.*;

public interface Validation{
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true; 
    }
    
    public static boolean isEmpty(String str) {
        if(str.equals(""))
            return true;
        return false;
    }
    
    public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isValidUsername(String username) {
        return username.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9_]{5,}$");
    }
    
    public boolean validateFields();
}
