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
public class LoginCodeAction extends ActionSupport{
    private final Map session = ActionContext.getContext().getSession();
    private String code;
    private String userId = (String) session.get("userid");

    
    public void validate(){
        if(!code.matches(Reg.STD)){
            addFieldError("code", Errors.CODE);
        }
    }
    public String execute(){
        if(DBAccountManager.codeIsValid(userId, code)){
            session.put("userid", userId);
            session.put("loggedin", true);
            return SUCCESS;
        }
        else{
            addFieldError("code", Errors.CODE);
            return INPUT;
        }
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
