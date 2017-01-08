package action;
 
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.opensymphony.xwork2.ActionSupport;
import database.DBChatManager;
import java.util.Map;
import constants.*;
import java.sql.Timestamp;

public class VoteOnTextAction extends ActionSupport{
    private Map<String, String[]> parameters = ServletActionContext.getRequest().getParameterMap();
    private String poster;
    private String voter;
    private String university;
    private String time;
    private boolean isUp = false;
 
    public String execute() throws Exception {
        poster = (String) parameters.get("poster")[0];
        voter = (String) parameters.get("voter")[0];
        university = (String) parameters.get("university")[0];
        time = (String) parameters.get("time")[0];
        if(parameters.get("isUp")[0].equals("true")) isUp = true;
        if(!poster.equals(voter)){
            DBChatManager.postVote(poster, time, voter, isUp, university);
            return SUCCESS;
        }
        else return ERROR;
    }
}
