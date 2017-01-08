package action;
import constants.*;
import com.opensymphony.xwork2.ActionSupport;
import database.DBAccountManager;
/**

 * @author Nicholas
 */
public class RegisterAction extends ActionSupport{
    private String userId;
    private String password;
    private String confirmPassword;
    private String email;
    private String university;

    public void validate(){
        if (!email.matches(Reg.EMAIL)){
            addFieldError("email", Errors.EMAIL);
        }
        if (!userId.matches(Reg.STD)){
            addFieldError("userId", Errors.STD);
        }
        if(!password.matches(Reg.STD)){
            addFieldError("password", Errors.STD);
        }
        if(!confirmPassword.equals(password)){
            addFieldError("confirmPassword", Errors.CONFIRM);
        }
    }
    public String execute(){
        String output = INPUT;
        if(DBAccountManager.entryExists("userInfo", "id", userId)){
            addFieldError("userId", Errors.ID);
        }
        else if(DBAccountManager.entryExists("userInfo", "email", email)){
            addFieldError("email", Errors.EMAILTAKEN);
        }
        else{
            DBAccountManager.createAccount(userId, password, email, university);
            output = SUCCESS;
        }
        return output;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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