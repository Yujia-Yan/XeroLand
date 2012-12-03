/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

import java.awt.Color;
import org.nightcode.demo.SkyX.Engine.Scheduler;
import org.nightcode.demo.SkyX.Framework.SkyXObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import org.nightcode.demo.SkyX.Engine.Clock;
import org.nightcode.demo.SkyX.Engine.TaskManager;
import org.nightcode.demo.SkyX.Engine.TimeBasedTask;
import org.nightcode.demo.SkyX.Framework.ITask;
import org.nightcode.demo.SkyX.Utility.Rnd;

/**
 *
 * @author dell
 */
public class Worker extends SkyXObject {

    
    public long birth;
    public double antennaAngle;
    public boolean antennaClockwise;
    public Brain brain;

    public antennaTask aTask;
    //用来决策的数据
    public int hp;
    public int color;
    public int type;
    public int detectedtype;
    public double aDistance;
    public double aFace;
    public double bFace;

    public Worker(Brain a) {
        super();
        r = Param.get().workerCollisionWidth;
        this.brain = a;
        this.hp = Param.get().hpMax;
        aTask = new antennaTask();
        type = 1;
        brain.setFitness(0.2);
    }

    public void Attack(Worker b) {
        synchronized (b) {
            if (b.hp <= 0) {
                return;
            }
            b.hp -= Param.get().attackPower;
            //synchronized(this)
            {

                // System.out.print(getIdentifier());
                //System.out.println(b.getIdentifier());
                this.hp += Param.get().attackPower;
                //fitness+=0.015;
            }
        }
        //fitness++;
    }

    public void Eat(Food b) {
        synchronized (b) {
            if (b.remain <= 0) {
                return;
            }
            b.remain -= Param.get().eatPower;
        }
        synchronized (this) {
            this.hp += Param.get().eatPower;

           
                brain.incFitness(1d);
        }
        
        //fitness+=Param.eatPower;

    }

    @Override
    public void execute(Scheduler a) {
        //System.out.print("run");
        //个体运动
        //synchronized(this)
        {


            //System.out.println(hp);

            if (hp > 0) {
                hp -= 5;
                //this.fitness++;
                //hp = Param.hpMax;
                //this.x = Rnd.getNextFloat(0, Param.width);
                //this.y = Rnd.getNextFloat(0, Param.height);

                if (brain.up()) 
                {

                    this.x += Param.get().velocity * brain.velocity * Math.sin(face);
                    this.y -= Param.get().velocity * brain.velocity * Math.cos(face);
                    if (this.x < 0) {
                        //this.x += Param.width;
                        this.x = 0;
                        //this.fitness+=this.hp/5;
                        // hp=-20;
                    }
                    if (this.y < -0) {
                        //this.y += Param.height;
                        this.y = 0;

                        //this.fitness+=this.hp/5;
                        //hp=-20;
                    }
                    if (this.x > Param.get().width) {
                        // this.x -= Param.width;
                        //hp=-20;
                        this.x = Param.get().width;

                        //this.fitness+=this.hp/5;
                        //hp=-20;
                    }
                    if (this.y > Param.get().height) {
                        this.y = Param.get().height;
                        //this.y=Param.height;

                        //this.fitness+=this.hp/5;
                        //hp=-20;
                    }
                }
                if (brain.left()) {
                    if (brain.rotate < -1) {
                        this.setFaceClockwise(-Param.get().rotateMax);
                    } else {
                        this.setFaceClockwise(brain.rotate * Param.get().rotateMax);
                    }

                }
                if (brain.right()) {
                    if (brain.rotate > 1) {
                        this.setFaceClockwise(Param.get().rotateMax);
                    } else {
                        this.setFaceClockwise(brain.rotate * Param.get().rotateMax);
                    }


                }
                //天线运动
                if (antennaClockwise) {
                    this.antennaAngle += Param.get().visionStep;
                } else {
                    this.antennaAngle -= Param.get().visionStep;
                }

                if (this.antennaAngle > Param.get().visionAngleRage) {
                    this.antennaAngle = Param.get().visionAngleRage;
                    antennaClockwise = !antennaClockwise;
                } else if (this.antennaAngle < -Param.get().visionAngleRage) {
                    this.antennaAngle = -Param.get().visionAngleRage;
                    antennaClockwise = !antennaClockwise;
                }
            } else {
                //return;
            }
        }

        // this.fitness++;
        this.start(this.getExecuteTime() + Param.get().objectMotionCalInterval);




    }

    public void draw(Graphics2D g) {
        //绘制本体
        if (hp <= 0) {
            return;
        }
        g.setColor(Color.red);
        g.setColor(new Color(color));
        AffineTransform a = new AffineTransform();
        a.translate(this.x, this.y);
        a.rotate(this.face);
        g.setTransform(a);
       
        g.drawLine(0, 0, 0, Param.get().attackRange);

        //绘制身体
        g.fillOval(-Param.get().workerCollisionWidth, -Param.get().workerCollisionWidth, Param.get().workerCollisionWidth * 2, Param.get().workerCollisionWidth * 2);

        g.setColor(Color.black);
        //g.drawString(Integer.toString(hp), 0, 0);

       // g.drawString(Double.toString(fitness), 0, 0);
        //g.drawString(Double.toString(aDistance), 10, 0);
        //绘制触角
        //a.rotate(antennaAngle);
       //g.setTransform(a);
        //g.setColor(Color.gray);
        //g.drawLine(0, 0, 0, -Param.instance.visionRage);

    }

    public void drawBest(Graphics2D g) {
        //绘制本体
        if (hp <= 0) {
            return;
        }
        AffineTransform a = new AffineTransform();
        a.translate(this.x, this.y);
        a.rotate(this.face);
        g.setTransform(a);
        //绘制武器

        g.setColor(Color.black);
        //g.drawString(Integer.toString(hp), 0, 0);

        g.drawString("你好世界！！！！", 0, 0);
        //g.drawString(Double.toString(aDistance), 10, 0);
        //绘制触角


    }

    @Override
    public void start(long time) {
        super.start(time);
    }

    public class antennaTask extends TimeBasedTask {
        //触角负责获取视界内的内容

        @Override
        public void execute(Scheduler a) {
            if (hp <= 0) {
                return;
            }

            AffineTransform aT = new AffineTransform();
            AffineTransform aT2 = new AffineTransform();
            aT.translate(x, y);
            aT.rotate(antennaAngle);
            aT2.translate(x, y);
            aT2.rotate(face);
            float tmp[] = {0, 0, 0, -Param.get().visionRage};
            aT.transform(tmp, 0, tmp, 0, 2);
            float tmp2[] = {0, 0, 0, -Param.get().attackRange};
            detectedtype=0;
            aT2.transform(tmp2, 0, tmp2, 0, 2);
{
             for (int ii=0;ii<UserInterface.instance.workerPool.size();ii++)
                //for (Worker i : UserInterface.instance.workerPool) 
                {
                 Worker i=UserInterface.instance.workerPool.get(ii);
                    if (i.getIdentifier() == getIdentifier()) {
                        continue;
                    }

                    if (i.hp <= 0) {
                        continue;
                    }
                    double tt = java.awt.geom.Line2D.ptSegDist(tmp[0], tmp[1], tmp[2], tmp[3], i.x, i.y);
                    if (tt < Param.get().visionCollisionSize) {
                        //synchronized (this) 
                        {
                            detectedtype = 1;
                            aDistance = distance(i);
                            aFace = i.face;
                            bFace = antennaAngle;
                            tt = java.awt.geom.Line2D.ptSegDist(tmp2[0], tmp2[1], tmp2[2], tmp2[3], i.getX(), i.getY());
                            if (tt < Param.get().workerCollisionWidth) {

                                //synchronized(i)
                                {
                                    Attack(i);
                                }
                            }
                            break;
                        }

                    }
                }
            }

                //synchronized(UserInterface.instance.foodPool)
            {
            
                for (int ii=0;ii<UserInterface.instance.foodPool.size();ii++) {
                    Food i=UserInterface.instance.foodPool.get(ii);
                    // System.out.println(java.awt.geom.Line2D.ptSegDist(tmp[0], tmp[1], tmp[2], tmp[3], i.getX(),i.getY()));
                    if (i.remain <= 0) {
                        continue;
                    }
                    double tt = java.awt.geom.Line2D.ptSegDist(tmp[0], tmp[1], tmp[2], tmp[3], i.getX(), i.getY());
                    if (tt < Param.get().visionCollisionSize) {

                        //synchronized (this) 
                        {
                            detectedtype = -1;
                            aDistance = distance(i);
                            aFace = 0;
                            bFace = antennaAngle;
                           
                            tt = java.awt.geom.Line2D.ptSegDist(tmp2[0], tmp2[1], tmp2[2], tmp2[3], i.getX(), i.getY());
                            // System.out.print(x);
                            //System.out.print(y);
                            //System.out.println(tt);

                            if (tt < Param.get().workerCollisionWidth) {
                                //synchronized(i)
                                {
                                    Eat(i);
                                }
                            }
                            break;
                        }

                    }

                }
            }
            if(detectedtype==0)aDistance=0;
            double[] input = {x/Param.get().width, y/Param.get().height,face,bFace,detectedtype,aDistance/Param.get().visionRage};
            // double[] input = {0,bFace-face,hp, type, detectedtype, aDistance, aFace-face};
            // double[] input = {face,bFace,hp, aFace, detectedtype, aDistance, 0};
            brain.eval(input);
            //aDistance=1000;
            //System.out.print("!");
            this.setExecuteTime(this.getExecuteTime() + Param.get().visionInterval);
            this.start();
        }
    }

    public void reset() {
        this.x = Rnd.getNextFloat(0, Param.get().width);
        this.y = Rnd.getNextFloat(0, Param.get().height);
        this.face = Rnd.getNextFloat((float) -Math.PI, (float) Math.PI);
        this.hp = Param.get().hpMax;
        this.brain.getAnn().reset();
    }
}
