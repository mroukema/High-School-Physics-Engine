/**
 * @author Peter Argall
 * @version 1.03 2009/12/3
 */

import java.awt.Rectangle;
/**
 *This Class holds information that is pertanent to the enviroment that the program has created. This includes global forces such as gravity and the size of the created enviroment
 **/
public class World {
	
	private double gravity; //Amount of gravity
	private double dampening;//amount of Dampening
	private Rectangle bounds;//Bounding area of this program
	
	/**
	 *Blank constructor setting gravity to 1 and dampening 0.
	 */
    public World() {
    	gravity = 1.0;//0=no gravity 1=full gravitational constant
    	dampening = 0.0;
    	bounds = new Rectangle(0,0,1000,1000);//creates a bounding box unlikely to create an error but in most cases will still need to be changed later
    }//end constructor
    
    /**
	 *Constructor
	 *@param gravity The gravity for this world
	 *@param dampening The Dampening for the world
	 */
    public World(double gravity, double dampening, Rectangle bounds) {
    	this.gravity = gravity;
    	this.dampening = dampening;
    	this.bounds = bounds;
    }//end constructor 
	
	/**Sets the Worlds gravity of the world,
	 *@param gravity Sets the gravity of the world
	 */
	public void setGravity(double gravity){
		this.gravity = gravity;
	}//end method setGravity()
	
	/**Returns the current amount of gravity
	 *@return the current value of gravity
	 */
	public double getGravity(){
		return gravity;
	}//end method getGravity()
	
	/**Sets the Worlds dampening of the world,
	 *@param dampening Sets the da of the world
	 */
	public void setDampening(double dampening){
		this.dampening = dampening;
	}//end method setDampening()
	
	/**Returns the current amount of dampening
	 *@return the current value of dampening
	 */
	public double getDampening(){
		return gravity;
	}//end method getDampening()
	/**
	 *Returns the bounds of this program
	 *@return The rectangle representing the bounds of this program
	 **/
	public Rectangle getBounds(){
		return bounds;
	}//end method getBounds()
	
	/**
	 *Set the nounds of this program
	 *@param bounds The rectangle to represent the bounds of this program 
	 **/
	public void setBounds(Rectangle bounds){
		this.bounds = bounds;
	}//end method setBounds
}