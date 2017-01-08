package bean;

import database.DBAccountManager;
import java.sql.SQLException;

/**
 *
 * @author Nick
 */
public class UserInfo{
    private String userId;
    private String email;
    private String university;

    public String setInfo(String userId) throws SQLException{
        this.userId = userId;
        email = DBAccountManager.getEntry("userInfo", userId, "email");
        university = DBAccountManager.getEntry("userInfo", userId, "university"); 
        return "success";
    }
    public String getUniversity(){
        return university;
    }
    public String getEmail(){
        return email;
    }
    public String getUserId(){
        return userId;
    }
}