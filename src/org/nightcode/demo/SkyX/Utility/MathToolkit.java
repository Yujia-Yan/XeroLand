package org.nightcode.demo.SkyX.Utility;
import java.lang.Math;
/** This class included methods to do some calculation
 * 
 * 
 *
 */
public class MathToolkit{
	//math constants
	public static final float PI=3.141592654f;
	public static final float EPSILON_E1=(float)(1E-1);
	public static final float EPSILON_E2=(float)(1E-2);
	public static final float EPSILON_E3=(float)(1E-3);
	public static final float EPSILON_E4=(float)(1E-4);
	public static final float EPSILON_E5=(float)(1E-5);
	//Color Masks
	public static final int R_MASK=0x00ff0000;
	public static final int G_MASK=0x0000ff00;
	public static final int B_MASK=0x000000ff;
	public static final int A_MASK=0xFF000000;
	
	//Sin cos table
	private static float sin[]=new float[360];
	private static float cos[]=new float[360];
	static{
		//initialize sin and cos 
		float theta=0;
		float delta=PI/180f;
		for(int i=0;i<360;i++){
			sin[i]=(float)java.lang.Math.sin(theta);
			cos[i]=(float)java.lang.Math.cos(theta);
			theta+=delta;
		}
	}
	public static float Sin(int angle){
		return sin[angle%360];
	}
	public static float Cos(int angle){
		return cos[angle%360];
	}
	public static float Tan(int angle){
		return Sin(angle)/Cos(angle);
	}

	//NUMERIC ALGORITHMs are taken  from Carmack's inplementation in QUAKE3.It's under license.
	//FastSqrt
	public static float Sqrt(float x){
		int convertor=Float.floatToIntBits(x);
		int convertor2=Float.floatToIntBits(x);
		convertor= 0x1FBCF800+(convertor>>1);
		convertor2=0x5f3759df-(convertor2>>1);
		return 0.5f*(Float.intBitsToFloat(convertor)+(x*Float.intBitsToFloat(convertor2)));
	}
	//the Inversion of Sqrt ,
	public static float InvSqrt(float x){
		float xhalf = 0.5f*x;
		int i = Float .floatToIntBits(x);
		i = 0x5f3759df - (i >> 1);
		x = Float .intBitsToFloat(i);
		x = x*(1.5f - xhalf*x*x); 
		return x;
	}
	/**Distance Calculation
	 * @param (x1,y1),(x2,y2)
	 * @result  the distance between two point
	 */
	public static int FastDistance(int x1,int y1,int x2,int y2){
		int x=Math.abs(x2-x1);
		int y=Math.abs(y2-y1);
		int mn=java.lang.Math.min(x, y);
		return (x+y-(mn>>1)-(mn>>2)+(mn>>4));
	}

	public static float Distance(float x1,float y1,float x2,float y2){
		float y=y2-y1;
		float x=x2-x1;
		return Sqrt(y*y+x*x);
	}


}
