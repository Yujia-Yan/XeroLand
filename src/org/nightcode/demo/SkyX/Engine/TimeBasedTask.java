package org.nightcode.demo.SkyX.Engine;

import org.nightcode.demo.SkyX.Framework.ITask;
import org.nightcode.demo.SkyX.Framework.ITimeBasedTask;

public class  TimeBasedTask implements ITimeBasedTask{
	 long timestamp;
	 long interval;
	 ITask task;
	public TimeBasedTask(ITask a,long time,long interval) {
		timestamp=time;
		this.interval=interval;
		task=a;
	}
	public TimeBasedTask(ITask a){
		task=a;
		timestamp=Clock.getInstance().getVirtualTIme();
	}
	public TimeBasedTask(){};
	public int compareTo(ITimeBasedTask o) {
		if(getExecuteTime()<o.getExecuteTime()) return -1;
		else return 1;
	}
	public void execute(Scheduler a) {
		if(task!=null)
		task.execute(a);
		putForward(interval);
		this.start();
	}
	public long getExecuteTime() {
		return timestamp;
	}
	public void putForward(long time){
		timestamp=timestamp+time;
	}
	public void setExecuteTime(long time) {
		timestamp=time;
		
	}
	public void start() {
		TaskManager.getInstance().addTask(this);
		
	}
	 
}