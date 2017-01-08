/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import constants.*;
import util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.TimeZone;
import datatype.Message;
import java.sql.Array;
/**
 *
 * @author Nick
 */
public class DBChatManager {
    public static Message[] getAllMessages(String university) throws SQLException{
        String id;
        String message;
        String time;
        int likes;
        int dislikes;
        ResultSet rs = Connector.selectQuery("SELECT * from " + Sql.DB + "." + Util.convertTo(university, ' ', '_') + " ORDER BY time ASC");
        Message[] messages = new Message[getNumberOfMessages(university)];
        int i = 0;
        while(rs.next()){
            time = rs.getString("time");
            id = rs.getString("id");
            message = rs.getString("message");
            likes = rs.getInt("likes");
            dislikes = rs.getInt("dislikes");
            messages[i] = new Message(time, id, message, likes - dislikes);
            i++;
        }
        return messages;
    }
    public static ResultSet getVoters(String id, String time) throws SQLException{
        ResultSet rs = Connector.selectQuery("SELECT * FROM " + Sql.DB + "." + Sql.VTS + " WHERE id='" +
                id + "' AND time='" + time + "';");
        return rs;
    }
    public static void postVote(String id, String time, String voter, boolean isUpVote, String university) throws SQLException{
        ResultSet rs = getVoters(id, time);
        if(!rsContains(rs, "voter", voter)){
            addVote(id, time, voter, isUpVote, university);
        }
    }
    private static boolean rsContains(ResultSet rs, String col, String value) throws SQLException{
        while (rs.next()) {
            if (rs.getString(col).equals(value)) return true;
        }
        return false;
    }
    public static void addVote(String id, String time, String voter, boolean isUpVote, String university) throws SQLException{
        Connector.createStatement("INSERT INTO " + Sql.DB + "." + Sql.VTS + " VALUES"
                + "('" + id + "', '" + time + "', '" + voter + "');");
        incrementVote(id, time, isUpVote, university);
    }
    public static void incrementVote(String id, String time, boolean isUpVote, String university) throws SQLException{
        String type;
        int numVotes=0;
        if(isUpVote)type = "likes";
        else type = "dislikes";
        ResultSet votes = Connector.selectQuery("SELECT * FROM " + Sql.DB + "." + Util.convertTo(university, ' ', '_')
        + " WHERE id='" + id + "' AND time='" + time + "';");
        if(votes.next()) numVotes = votes.getInt(type);
        numVotes++;
        Connector.createStatement("UPDATE " + Sql.DB + "." + Util.convertTo(university, ' ', '_') +
                " SET " + type + " = " + numVotes + ", time='" + time + "' WHERE id = '" + id + "' AND time = '" + time + "';");
    }
    
    public static void postMessage(String id, String university, String message) throws SQLException{
        Connector.createStatement("INSERT INTO " + Sql.DB + "." + Util.convertTo(university, ' ', '_') + " VALUES"
                + "('" + getTime() + "', '" + id + "', '" + message + "', '0', '0');");
        if (getNumberOfMessages(university)>Max.MSGNUM) removeEarliestMessage(university);
    }
    public static Timestamp getTime(){
        return new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime());
    }
    public static int getNumberOfMessages(String university) throws SQLException{
        ResultSet rs = Connector.selectQuery("SELECT COUNT(*) FROM " + Sql.DB + "." + Util.convertTo(university, ' ', '_'));
        rs.next();
        return rs.getInt(1);
    }
    public static void removeEarliestMessage(String university) throws SQLException{
        String id;
        String time;
        ResultSet rs;
        rs = Connector.selectQuery("SELECT * FROM " + Sql.DB + "."+ Util.convertTo(university, ' ', '_') + " ORDER BY time ASC LIMIT 1;");
        rs.next();
        id = rs.getString("id");
        time = rs.getString("time");
        Connector.createStatement("DELETE FROM " + Sql.DB + "."+ Sql.VTS + " WHERE id='" + id + "' AND time='" + time + "';");    
        Connector.createStatement("DELETE FROM " + Sql.DB + "."+ Util.convertTo(university, ' ', '_') + " ORDER BY time ASC LIMIT 1;");    
    }
}
