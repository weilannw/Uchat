/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import constants.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Mysql connector class
 * @author Nick
 */
public class Connector {
    private static Connection connection = establishConnection();
    
    private static Connection establishConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.err.println(e);
            return null;
        }
        try{
            return DriverManager.getConnection(Sql.CON, Sql.USER, Sql.PASS);
        }
        catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    public static Statement createStatement(String str){
        Statement statement = null;
        try{
            statement = connection.createStatement();
            statement.executeUpdate(str);
            return statement;
        }
        catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    public static ResultSet selectQuery(String str){
        Statement statement = null;
        try{
            statement = connection.createStatement();
            return statement.executeQuery(str);
        }
        catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    public static boolean resultSetNotEmpty(String statement){
        try{
            return selectQuery(statement).next();
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }
}
