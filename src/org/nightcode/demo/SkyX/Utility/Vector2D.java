 package org.nightcode.demo.SkyX.Utility;
import org.nightcode.demo.SkyX.Utility.MathToolkit;


/** this class implements Vector2D
 * 
 * 
 *
 */
public class Vector2D{
	float x;
	float y;
	public Vector2D(){
		
	}
	public Vector2D(float m_x,float m_y){
		x=m_x;
		y=m_y;
	}
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}
	/**
	 * @return the y 
	 */
	public float getY() {
		return y;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * @param x and y to set
	 */
	public void setXY(float x,float y) {
		this.x = x; 
		this.y = y;
	}
	/**
	 * 
	 */
	public Vector2D scaleProduct(float a){
		return new Vector2D(x*a, y*a);
	}
	/**
	 * dot product
	 * @param the vector to be doted;
	 * @return the result of the dot product
	 */
	public float  dot(Vector2D b){
		return this.x*b.getX()+this.y*b.getY();
	}
	/**
	 * add
	 * @param the vector to be added;
	 * @return the result
	 */
	public Vector2D  add(Vector2D b){
		return new Vector2D(this.x+b.getX(),this.y+b.getY());
	}
	/**
	 * minus
	 * 
	 * @return the result
	 */
	public Vector2D minus(Vector2D b){
		return new Vector2D(this.x-b.getX(),this.y-b.getY());
	}
	/**get the norm of the vector
	 * 
	 */
	public Vector2D getNorm(){
		float inv=MathToolkit.InvSqrt(x*x+y*y);
		return new Vector2D(x*inv,y*inv);
	}
	/**mod,get the mod of the vector
	 * 
	 */
	public float getMod()
	{
		return MathToolkit.Distance(x, y, 0, 0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}
	
}