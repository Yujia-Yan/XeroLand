package org.nightcode.demo.SkyX.Utility;

import java.util.*;
/** this class encapsulated some utilities 
 * 
 * 
 *
 */
public  class Rnd {
	private static Random rnd;
	static{
		rnd=new Random();
	}
	public static int getNextInt(int low,int high){
		if(low==high) return low;
		else return rnd.nextInt(high-low)+low;
		
	}
	public static float getNextFloat(float low,float high){
		if(low==high) return low;
		else return rnd.nextFloat()*(high-low)+low;
	}
}
