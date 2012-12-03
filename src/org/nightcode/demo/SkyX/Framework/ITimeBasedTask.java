package org.nightcode.demo.SkyX.Framework;

public interface ITimeBasedTask extends ITask,Comparable<ITimeBasedTask>{
	long getExecuteTime();
	void setExecuteTime(long time);
}
