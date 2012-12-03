/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

import java.io.Serializable;

/**
 *
 * @author yan
 */
public  class Param implements Serializable{
    public  int NumOfWorker=20;
    public  int NumOfFood=20;
    public  int elitism=4;
    public  int inputNodes=6;
    public  int hidNodes=20;
    public  int hidLayers=2;
    public  int outputNodes=8;
    public  long objectMotionCalInterval=50; 
    public  float objectVelocityMax=8f;
    public  float rotateMax=0.8f;
    public  float velocity=15;
    public  float width;
    public  float height;
    public  float visionAngleRage=(float) (0.5*Math.PI);
    public  int visionRage=100;
    public  float visionStep=0.4f;
    public  long visionInterval=57;
    public  long visionCollisionSize=25;
    public  int attackPower=15;
    public  int eatPower=500;
    public  int foodRemain=15;
    public  int attackRange=25;
    public  int hpMax=1000;
    public  int workerCollisionWidth=15;
    public  float mutationRate=0.15f;
    
        public double totalFitness;
        public double minFitness;
        public double maxFitness;
        public double maxWeight;
        public double minWeight;
        public int generation;
    public static Param instance;
    public static void set(Param a){
        instance=a;
    }
     public static Param get(){
         if(instance==null)
        instance=new Param();
         return instance;
    }
       
}
