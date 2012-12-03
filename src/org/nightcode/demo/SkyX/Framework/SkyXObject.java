/**this class define the GameObject which has a random-generated identifier
 * ,  coordinate , a method for collide detection;
 * 
 */
package org.nightcode.demo.SkyX.Framework;

import java.util.Vector;

import org.nightcode.demo.SkyX.Engine.*;
import org.nightcode.demo.SkyX.Utility.MathToolkit;
import org.nightcode.demo.SkyX.Utility.Rnd;

public abstract class SkyXObject implements ITimeBasedTask{
	protected float x;
	protected float y;
	protected float r;
	protected float face;//the angle counted counter-clockwise
	protected int identifier;
        
	protected long nextExecuteTime;
	ISprite curSprite;
	private static int i;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	static{
		i=0;
	}
        public int getIdentifier(){
            return identifier;
        }
	public static int getNextIndex(){
		i++;
		return i;
	}
	protected SkyXObject(){
		identifier=getNextIndex();
		nextExecuteTime=0;
		
	}
	public int hashCode() {
		return identifier;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(ITimeBasedTask obj) {
		if (this == obj)
			return true;
		return false;
	}
	public float distance(SkyXObject a){
		return MathToolkit.Distance(a.x, a.y, this.x, this.y);
	}
	public boolean collideWith(SkyXObject a){
		if(distance(a)<r+a.r) return true;
		else return false ;
	}

	public int compareTo(ITimeBasedTask o) {
		
		if(getExecuteTime()<o.getExecuteTime()) return -1;
		else return 1;
	}
	public long getExecuteTime() {
		return nextExecuteTime;
	}
	public void setExecuteTime(long time) {
		nextExecuteTime=time;
		
	}

	public void setFaceClockwise(float angle){
		face=angle+face;
                if(face>Math.PI) face-=2*Math.PI;
                else if(face<-Math.PI) face+=2*Math.PI;
	}
        public float getFace(){
            return face;
        }
	public void setCurSprite(ISprite a){
		curSprite=a;
	}
	public void setRadius(float n){
		r=n;
	}
	public float getRadius(){
		return r;
	}

	public  void start(long time){
                setExecuteTime(time);
		TaskManager.getInstance().addTask(this);
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public  void setX(float x){
		this.x=x;
	}
	public void setY(float y){
		this.y=y;
	}
	

}
