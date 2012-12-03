/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import org.nightcode.demo.SkyX.Engine.Scheduler;
import org.nightcode.demo.SkyX.Framework.SkyXObject;
import org.nightcode.demo.SkyX.Utility.Rnd;

/**
 *
 * @author dell
 */
public class Food extends SkyXObject {
   public int remain;
   public int type;
   
   public Food(){
       remain=Param.get().foodRemain;
       this.type=-1;
   }

   //食物残量随时间减少，当减少到零时，食物重新初始化
    @Override
    public void execute(Scheduler a) {
        //remain-=1;
        {
        if(remain<=0) {
            //重设
            //System.out.print("!");
            remain=Param.get().foodRemain;
            this.x=Rnd.getNextFloat(0+10, Param.get().width-10);
            this.y=Rnd.getNextFloat(0+10, Param.get().height-10);
            this.start(this.getExecuteTime()+500);
            return;
        }
        }
        this.start(this.getExecuteTime()+50);
    }
    public void draw(Graphics2D g){
        //绘制本体
        g.setColor(Color.yellow);
        AffineTransform a=new AffineTransform();
        a.translate(this.x, this.y);
        a.rotate(this.face);
        g.setTransform(a);
        g.fillOval(-15, -15, 30, 30);
        
        g.setColor(Color.black);
        //g.drawString(Double.toString(remain), 0, 0);
    }
   
}
