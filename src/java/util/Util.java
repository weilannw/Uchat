/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Nick
 */
public class Util {
    public static String convertTo(String str, char from, char to){
        String con = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == from){   
                con+= to;
            }
            else{
                con+= str.charAt(i);
            }
        }
        return con;
    }
}
