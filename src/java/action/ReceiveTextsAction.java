package action;
 
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.opensymphony.xwork2.ActionSupport;
import database.DBChatManager;
import java.util.Map;
import constants.*;
import datatype.Message;

public class ReceiveTextsAction extends ActionSupport{
    private Map<String, String[]> parameters = ServletActionContext.getRequest().getParameterMap();
    private InputStream inputStream;
    private String university;
    public InputStream getInputStream(){
        return inputStream;
    }
 
    public String execute() throws Exception {
        university = (String) parameters.get("university")[0];
        Message[] messages = DBChatManager.getAllMessages(university);
        inputStream = 
        new ByteArrayInputStream(convertAll(messages).getBytes("UTF-8"));
        
        return SUCCESS;
    }
    private String convertAll(Message[] messages){
        String html = "";
        for(int i = 0; i < messages.length; i++){
            html += convertToHtml(messages[i]);
        }
        return html;
    }
    private String convertToHtml(Message message){
        return "<div class='message'>"
                + "<div class='timestamp'>" + message.time + "</div>"
                + "<div class='usr'>@" + message.id + "</div>"
                + "<div class='msg'>" + message.msg + "</div>" 
                + "<div class='votes'>"
                + "<button class='up' onclick=\"new function(){vote('" + message.time + "', '" + message.id + "', 'true');}\">&#x25b3;</button>"
                +"<div class='num-votes'>" + message.rating + "</div>"
                + "<button class='down' onclick=\"new function(){vote('" + message.time + "', '" + message.id + "', 'false');}\">&#x25bd;</button>"
                + "</div></div>";
    }
}
