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
public class EditProfileAction extends ActionSupport{
    private String email;
    private String university;
    
    private final Map session = ActionContext.getContext().getSession();
    
     public void validate(){
        if (!email.matches(Reg.EMAIL)){
            addFieldError("email", Errors.EMAIL);
        }
    }
    public String execute(){
        String userId = (String)session.get("userid");
        DBAccountManager.editEntry("userInfo", userId, "email", email);
        DBAccountManager.editEntry("userInfo", userId, "university", university);
        return SUCCESS;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
