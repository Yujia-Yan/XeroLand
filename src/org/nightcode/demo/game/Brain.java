/*
 * 大脑
 * 
 */
package org.nightcode.demo.game;

import java.io.Serializable;
import org.nightcode.demo.neuralNetwork.NeuralNetwork;

/**
 *
 * @author dell
 */
public abstract class Brain implements Serializable {
    ////////////////////////////
    public static int NOTHING=0;
    public static int LEFT=1;
    public static int RIGHT=2;
    public static int UP=3;
    public static int DOWN=4;
    public static int LEFT_UP=5;
    public static int LEFT_DOWN=6;
    public static int RIGHT_UP=7;
    public static int RIGHT_DOWN=8;
    transient boolean up;
    transient boolean down;
    transient boolean left;
    transient boolean  right;
    transient public float rotate;
    transient public float velocity;
    private double fitness;
    ///////////////////////////////////
   public boolean up(){
   return up;
    }
    public boolean down(){
        return down;
    }
    public boolean left(){
        return left;
    }
    public boolean right(){
        return right;
    }
    public void eval(double[] input){
        
    }
    public NeuralNetwork getAnn(){
        return null;
    }
    public synchronized void setFitness(double f){
        fitness=f;
    }
    public synchronized double getFitness(){
        return fitness;
    }
    public synchronized void incFitness(double a){
        fitness+=a;
    }
    
}
