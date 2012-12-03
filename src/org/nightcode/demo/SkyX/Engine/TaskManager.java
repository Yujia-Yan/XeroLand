/**
 * 
 */
package org.nightcode.demo.SkyX.Engine;

import java.util.PriorityQueue;
import org.nightcode.demo.SkyX.Framework.ITask;
import org.nightcode.demo.SkyX.Framework.ITimeBasedTask;
import org.nightcode.demo.SkyX.Utility.Priority_Queue;
 
/**
 * @author dell
 *
 */
public class TaskManager {

	public  final int Capacity=6000;
	//Priority_Queue timeBasedTask;
        java.util.PriorityQueue<ITimeBasedTask>timeBasedTask;
	TimeBasedTask renderTask;
	long renderInterval;
	TaskManager(){
		timeBasedTask=new PriorityQueue<ITimeBasedTask>();
	}
	public  ITimeBasedTask getNext(){
           
		ITimeBasedTask a=(ITimeBasedTask)timeBasedTask.peek();
		if(a!=null)
		return a;
		else return null;
            
	}
	public  ITimeBasedTask pop(){
            
            synchronized(this){
		return timeBasedTask.poll();
            }
            
	}
        public void clear(){
            synchronized(this){
                timeBasedTask.clear();
            }
        }

	public  void addTask(ITimeBasedTask a){
            synchronized(this){
		timeBasedTask.add(a);
            }
	}
	public int getSize(){
		return timeBasedTask.size();
	}

	
	/////////////////////////
	static TaskManager instance;
	public static TaskManager getInstance(){
		if(instance==null) instance=new TaskManager();
		return instance;
	}

}
