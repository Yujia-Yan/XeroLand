/**
 *
 */
package org.nightcode.demo.SkyX.Engine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nightcode.demo.SkyX.Framework.ITimeBasedTask;

/**
 * schedule in-game tasks
 *
 *
 */
public class Scheduler {

    runningThread[] runningThreads;
    //taskManger

    Scheduler() {
    }
    static Scheduler instance;

    public static Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    class runningThread extends Thread {

        private boolean running;

        public runningThread() {

            running = true;
        }

        public void t_stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                //System.out.println("s");
                execute();
                try {
                    sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void start(int runningThread) {
        runningThreads = new runningThread[runningThread];
        for (int i = 0; i < runningThread; i++) {
            runningThreads[i] = new runningThread();
            runningThreads[i].start();
        }

        Clock.getInstance().start();
    }

    public void stop() {
        Clock.getInstance().stop();
        for (int i = 0; i < runningThreads.length; i++) {
            if (runningThreads[i] != null) {
                runningThreads[i].t_stop();
            }
        }
    }

    public long getClockTime() {
        return Clock.getInstance().getClockTime();
    }

    public long getVirtualTime() {
        return Clock.getInstance().getVirtualTIme();
    }

    public void execute() {
        Clock clock = Clock.getInstance();
        TaskManager taskManager = TaskManager.getInstance();

        //System.out.println(taskManager.timeBasedTask.size());

        //task based on time,virtual time is used here
        long end = clock.getFrameEndTime();
        //System.out.println(end-getVirtualTime());
        ITimeBasedTask tmp=null;
        boolean flag=true;
        while(flag)
         {
            tmp = taskManager.pop();
            end = clock.getFrameEndTime();
        
            //System.out.println(end);
            if (tmp != null && tmp.getExecuteTime() < end) {
                 tmp.execute(this);
            } else {
                if(tmp!=null){
                taskManager.addTask(tmp);
                
                    clock.advanceToEnd();
                    clock.beginFrame();
                }
                tmp = null;
                flag=false;
            }
         }
        
        //System.out.println(tmp.getExecuteTime());

       // synchronized (taskManager) 

        //System.out.println(Clock.getInstance().getFrameCount());
        //System.out.println("size:"+taskManager.getSize());
        //System.out.println("executing");
        //System.out.println("executing...done");
        //end the frame in virtual time
        //frame task
//		//if(taskManager.renderTask.timestamp<end){
//			taskManager.renderTask.execute(this);
        //taskManager.renderTask.timestamp+=taskManager.renderInterval;
        //System.out.println("rendering~");
        //}
        //System.out.println("frameEnd"+end);
        //render task
    }
}
