/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import constants.*;
import java.sql.SQLException;
import util.Util;

/**
 *
 * @author Nick
 */
public class GenerateDatabase {
    public static void createChatRooms(){
        String con;
        for(int i = 0; i < Uni.list.length; i++){
            con = Util.convertTo(Uni.list[i], ' ', '_');
            createUniversityChat(con);
        }
    }
    public static void createTable(String tbl, String params[]){
        String stmt = "CREATE TABLE " + Sql.DB + "." + tbl + "(";
        for(int i = 0; i < params.length; i++){
            stmt += params[i];
            if(i < params.length - 1) stmt += ", ";
        }
        stmt += ");";
        Connector.createStatement(stmt);
    }
    public static void createUniversityChat(String university){
        createTable(university, new String[]{"time TIMESTAMP PRIMARY KEY", "id VARCHAR(20)", "message VARCHAR(150)", "likes INT", "dislikes INT"});
    }
    public static void createTbls(){
        createChatRooms();
        createTable(Sql.USR, new String[]{"id VARCHAR(20) PRIMARY KEY", "password VARCHAR(50)", "email VARCHAR(50)", "university VARCHAR(50)"});
        createTable(Sql.LGN, new String[]{"id VARCHAR(20) PRIMARY KEY", "code VARCHAR(50)"});
        createTable(Sql.VTS, new String[]{"id VARCHAR(20)", "time TIMESTAMP", "voter VARCHAR(20)"});
    }
    public static void main(String args[]) throws SQLException{
        createTbls();
    }
}
