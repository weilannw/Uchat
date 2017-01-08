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
public class Reg{ 
    public static final String STD = "^[a-zA-z0-9-]{5,20}$";
    public static final String EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+" +
            "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,50})$";
}
