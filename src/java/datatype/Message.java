/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

/**
 *
 * @author Nick
 */
    public class Message{
        public String time;
        public String id;
        public String msg;
        public int rating;
        public Message(String time, String id, String msg, int rating){
            this.id = id;
            this.msg = msg;
            this.rating = rating;
            this.time = time;
        }
    }
