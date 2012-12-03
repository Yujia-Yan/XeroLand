
package org.nightcode.demo.SkyX.Engine;

/**Clock class update both the virtual and Real time
 * Idea captured form "Game Programming Gems 3"
 *
 */
public class Clock {
	private double factor=1;
	//whether it is active or not
	private boolean running;
	//Current hardware clock,clock time 
	//and hardware time elapsed in the same  way
	//but the virtual time recorded the time the clock actually works
	//that is virtualTime
	private long currentHardwareTime;
	//record the last hardware clock when calling update();
	private long lastTime;
	//the time since the clock started
	private long clockTime;
	//the offset between clock and hardware time
	
	//the time when the clock paused
	private long pauseAt;
	//virtual time starts from 0,used to process virtual Tasks
	private long virtualTime;
	//the time the clock paused
	private long pausedTime;
	//the time about frame
	private long frameStartTime;
	private long frameEndTime;
	private long frameCount;
	private long clockOffset;
	private long frameLength;
	/**reset the clock
	 * 
	 */
	public Clock(){
		
		reset();
	}
	public synchronized void reset(){
		
			running=false;
			currentHardwareTime=System.currentTimeMillis();
			lastTime=currentHardwareTime;
			clockTime=0;
			clockOffset=currentHardwareTime-clockTime;
			pauseAt=0;
			virtualTime=0;
			pausedTime=0;
			frameStartTime=0;
			frameEndTime=0;
			frameCount=0;
	}
	/**update the clock
	 * 
	 */
	public synchronized void update(){
		lastTime=currentHardwareTime;
		currentHardwareTime=System.currentTimeMillis();
		clockTime+=(currentHardwareTime-lastTime)*factor;
	}
	public synchronized void start(){
		if(!running){
			running=true;
			update();
			pausedTime+=clockTime-pauseAt;
		}
	}
	public synchronized void stop(){
		if(running){
			running=false;
			update();
			pauseAt=clockTime;
		}
	}
	public boolean isRunning(){
		return this.running;
	}
	public synchronized void beginFrame(){
		if(running){
			update();
			frameCount++;
			virtualTime=frameEndTime;
			frameStartTime=virtualTime;
			frameEndTime=clockTime-pausedTime;
			frameLength=frameEndTime-frameStartTime;
		}
	}
	public synchronized void advanceToEnd(){
		if(running){
			virtualTime=frameEndTime;
		}
	}
	public long getClockTime(){
		update();
		return clockTime;
	}
	public long getVirtualTIme(){
		return virtualTime;
	}
	public long getFrameStart(){
		return frameStartTime;
	}
	/**
	 * @return the frameEndTime
	 */
	public long getFrameEndTime() {
		return frameEndTime;
	}
	/**
	 * @return the frameCount
	 */
	public long getFrameCount() {
		return frameCount;
	}

	public long getClockOffset() {
		return clockOffset;
	}

	static Clock instance;
	public static Clock getInstance(){
		if(instance==null) instance=new Clock();
		return instance;
	}
	public void setFactor(double n){
            if(n>0)
		factor=n;
	}
	public double getFactor(){
		return factor;
	}
}
