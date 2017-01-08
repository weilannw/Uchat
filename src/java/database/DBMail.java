/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.DBAccountManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Nick
 */
public class DBMail{
    public static void sendEmail(String id, String email, String password) throws SQLException{
            
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable","false"); 
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");
            Session mailSession = null;

            mailSession = Session.getInstance(props,  
                    new javax.mail.Authenticator() {  
                protected PasswordAuthentication getPasswordAuthentication() {  
                    return new PasswordAuthentication("uchatbot@gmail.com", "Element123/r");  
                }  
            });  


            try {

                Transport transport = mailSession.getTransport();

                MimeMessage message = new MimeMessage(mailSession);

                message.setSubject("UChat Login Code Request");
                message.setFrom(new InternetAddress("UChatBot@U-Chat.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                String body = "<p>Hello " + id + ",<br><br>"
                        + "here is your temporary login code.<br><br>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;Code: " + password + "<br><br>" 
                        + "You can use it to log in and then change your password from there. "
                        + "Please do not respond to this message. <br><br>"
                        + "Thank you,<br><br>"
                        + "      The U-Chat Team</p>";
                message.setContent(body,"text/html");
                transport.connect();

                transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
                transport.close();
            } 
            catch (Exception e) {
        }         
    }        
}
