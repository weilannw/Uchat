/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import constants.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.RandomStringUtils;
import java.sql.*;

/**
 *
 * @author Nick
 */
public class DBAccountManager {
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-";
    public static boolean genCodeAndSend(String id, String email) throws SQLException{
        String code = "";
        String hashedCode = "";
        if(pairIsValid(Sql.USR, id, "email", email)){
            code = generateCode();
            hashedCode = hashPassword(code);
            if(entryExists(Sql.LGN, "id", id)) editEntry(Sql.LGN, id, "code", hashedCode);
            else addLoginCode(id, hashedCode);
            DBMail.sendEmail(id, email, code);
            return true;
        }
        else return false;
    }
    public static boolean codeIsValid(String id, String code){
        if (pairIsValid(Sql.LGN, id, "code", hashPassword(code))){
            rmLoginCode(id);
            return true;
        }
        else return false;
    }
    public static String generateCode(){
        return RandomStringUtils.random(15, ALPHA);
    }
    public static void addLoginCode(String id, String code){
        Connector.createStatement("INSERT INTO " + Sql.DB + "." + Sql.LGN + " VALUES('" + id + "', '" + code + "');");
    }
    public static void rmLoginCode(String id){
        Connector.createStatement("DELETE FROM " + Sql.DB + "." + Sql.LGN + " WHERE id = '" + id + "';");
    }
    public static String getEntry(String table, String id, String type) throws SQLException{
        ResultSet rs = Connector.selectQuery("SELECT " + type + " FROM " + Sql.DB + "." + table + " WHERE id = '" + id + "';");
        rs.next();
        return rs.getString(type);
    }
    public static boolean entryExists(String table, String type, String value){
        return Connector.resultSetNotEmpty("SELECT id FROM " + Sql.DB + "." + table + " WHERE " + type +"= '" + value + "';");
    }

    //editing profile
    public static void editEntry(String table, String id, String type, String entry){
        Connector.createStatement("UPDATE " + Sql.DB + "." + table + " SET " + type + " = '" + entry + "' WHERE id = '" + id + "';");
    }
    public static void changePassword(String id, String password){
         editEntry(Sql.USR, id, "password", hashPassword(password));
    }
    //account creation and validation
    public static void createAccount(String id, String password, String email, String university){
        Connector.createStatement("INSERT INTO " + Sql.DB + "." + Sql.USR + " VALUES('" + id + "', '" + hashPassword(password) + "', '" + email + "', '" + university + "');");
    } 
    public static boolean pairIsValid(String table, String id, String type, String value){
       return Connector.resultSetNotEmpty("SELECT * FROM  " + Sql.DB + "." + table + " WHERE id = '" + id + "' AND " + type + " = '" + value + "';");
    }
    public static boolean accountIsValid(String id, String password){
        String hashedPassword = hashPassword(password);
        rmLoginCode(id);
        return pairIsValid(Sql.USR, id, "password", hashedPassword);
    }
    private static String hashPassword(String password) {
       String digest;
       try {
           MessageDigest md = MessageDigest.getInstance("md5");
           md.reset();
           byte[] bytes = md.digest(password.getBytes());
           digest = new BigInteger(1, bytes).toString(16);
       }
       catch (NoSuchAlgorithmException nsae) {
           nsae.printStackTrace();
           digest = null;
       }
       return digest;
  }
}
