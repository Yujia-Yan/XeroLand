/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

/**
 *
 * @author dell
 */
public class KeyBoardBrain extends Brain{

    public KeyBoardBrain(){
        super();
    }

    public synchronized void setUp(){
        up=true;
        this.velocity=1;
       //System.out.println("asd");
    }
    public synchronized void resetUp(){
       up=false;
       
        this.velocity=0;
    }
    public synchronized void setDown(){
        down=true;
    }
    public synchronized void resetDown(){
        down=false;
    }
    public synchronized void setLeft(){
       left=true;
       rotate=-Param.get().rotateMax;
    }
    public  synchronized void resetLeft(){
        left=false;
        rotate=0;
    }
    public  synchronized void setRight(){
        right=true;
        
       rotate=Param.get().rotateMax;
    }
    public  synchronized void resetRight(){
       right=false;
       
       rotate=0;
    }
    
}
