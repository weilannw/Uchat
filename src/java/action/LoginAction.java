/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import constants.*;
import database.DBAccountManager;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class LoginAction extends ActionSupport{
    private String id;
    private String pass;
    private final Map session = ActionContext.getContext().getSession();
    
    public String execute(){
        if(!(id.matches(Reg.STD) && pass.matches(Reg.STD) && DBAccountManager.accountIsValid(id, pass))){
            addFieldError("id", Errors.LOGIN);
            return INPUT;
        }
        else{
            session.put("userid", id);
            session.put("loggedin", true);
            return SUCCESS;
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
