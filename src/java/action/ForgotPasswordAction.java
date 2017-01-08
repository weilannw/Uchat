/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;
import  constants.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import database.DBAccountManager;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class ForgotPasswordAction extends ActionSupport{
    private String email;
    private String userId;
    private final Map session = ActionContext.getContext().getSession();

    public String execute() throws SQLException{
        if(DBAccountManager.genCodeAndSend(userId, email)){
            session.put("userid", userId);
            return SUCCESS;
        }
        else{
            addFieldError("email", Errors.FORGOT);
            return INPUT;
        }
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
