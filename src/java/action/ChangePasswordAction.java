/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import constants.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import database.DBAccountManager;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class ChangePasswordAction extends ActionSupport{
    private String password;
    private String confirmPassword;
    private final Map session = ActionContext.getContext().getSession();
    private String userId = (String) session.get("userid");
    
    public void validate(){
        if(!password.matches(Reg.STD)){
            addFieldError("password", Errors.STD);
        }
        if(!confirmPassword.equals(password)){
            addFieldError("confirmPassword", Errors.CONFIRM);
        }
    }
    public String execute(){
        DBAccountManager.changePassword(userId, password);
        return SUCCESS;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    } 
}
