/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author Nick
 */
public class Sql {
    public static final String USER = "root";
    public static final String PASS = "element123/r";
    public static final String DB = "uchat";
    public static final String CON = "jdbc:mysql://localhost:3306/" + DB + "?useSSL=false";
    //table names
    public static final String USR = "userInfo";
    public static final String LGN = "loginCodes";
    public static final String VTS = "votes";
    
    /*public static final String USER = "team12";
    public static final String PASS = "l%ma8";
    public static final String DB = USER + "_db";
    public static final String CON = "jdbc:mysql://localhost:3306/" + DB + "?useSSL=true";
    //table names
    public static final String USR = "userInfo";
    public static final String LGN = "loginCodes";
    public static final String VTS = "votes";*/
}
