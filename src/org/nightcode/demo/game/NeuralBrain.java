/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

import org.nightcode.demo.neuralNetwork.NeuralNetwork;

/**
 *
 * @author dell
 */
public class NeuralBrain extends Brain{
    NeuralNetwork ann;
    
    public NeuralBrain(NeuralNetwork a){
        ann=a;
    }

    @Override
    public void eval(double[] input) {
        
        double tmp[]=ann.eval(input);
        //ann.mutate();
        
        //System.out.println();
        this.up=tmp[0]>tmp[1];
        this.down=! this.up;
        this.left=tmp[2]>tmp[3];
        this.right= !right;
        rotate=(float)(tmp[2]-tmp[3]);
        velocity=(float)(tmp[0]-tmp[1]);
    }

    @Override
    public NeuralNetwork getAnn() {
        return ann;
    }
    
    
}
