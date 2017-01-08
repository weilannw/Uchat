package action;
 
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.opensymphony.xwork2.ActionSupport;
import database.DBChatManager;
import java.util.Map;
import constants.*;

public class SendTextAction extends ActionSupport{
    private Map<String, String[]> parameters = ServletActionContext.getRequest().getParameterMap();
    private String userid;
    private String university;
    private String message;
 
    public String execute() throws Exception {
        userid = (String) parameters.get("userid")[0];
        university = (String) parameters.get("university")[0];
        message = (String) parameters.get("message")[0];
        if (message.length() > Max.MSGLEN){
            message = message.substring(0, Max.MSGLEN);
        }
        else if(message.length() == 0){
            return ERROR;
        }
        DBChatManager.postMessage(userid, university, message);
        return SUCCESS;
    }
}
