/**
 * @(#)RectangularBoundObject.java
 *
 * Used to create a Rectanglar Object
 *
 * @author Mitchell Grenier, Mark Roukema
 * @version 1.05 2009/12/3
 */

import java.awt.geom.*;
import java.awt.*;
/**
 *Class that has all the fields and methods pertaining to on screen rectangular objects
 *@see PhysicsObject
 *@see RotatingRect
 **/
public class RectangularBoundObject extends PhysicsObject{
	private int width, 			//The width of the object
				height;			//The height of the object
	private RotatingRect shape;	//Contains the polygon and the methods to manipulate the polygon
	private double rotation;	//The amount in radians the object is rotating
	/**
	 *Creates a Rectangular Bound Object
	 *
	 *@param width Sent in to create a retanglar bound object with given width
	 *@param height Sent in to create a retanglar bound object with given height
	 *@param weight The weight of the object
	 *@param velocity The velocity of the object
	 *@param quadrent The quadrent object is located in
	 *@param elasticity The elasticity of the object
	 *@param location The location of the object
	 *@param rotation The amount the object will rotate
	 */
    public RectangularBoundObject(int width, int height, int weight, Point2D.Double velocity, int quadrent, double elasticity, Point2D.Double location, double rotation){
    	super(weight, velocity, quadrent, elasticity, location);//call to the PhysicsObject constructor
    	this.width = width;
    	this.height = height;
    	this.rotation = rotation; 
    	shape = new RotatingRect(width,height,(int)location.x+(width/2),(int)location.y+(height/2));
    }//end constructor
    
    /**
     *Gets the width of the current object
     *
     *@return Returns the width seeing as the user asked for it
     **/
    public int getWidth(){
    	return width;
    }//end getWidth()
    
   /**
     *Gets the height of the current object
     *
     *@return Returns the height seeing as the user asked for it
     **/
    public int getHeight(){
    	return height;
    }//end getHeight()
    
    /**
     *Set the width of an object for dynamic object change
     *@param width  The new width value of the object
     **/
    public void setWidth(int width){
    	this.width = width;
    }//end setWidth()
    
    /**
     *Set the height of an object for dynamic object change
     *@param height  The new height value of the object
     **/
    public void setHeight(int height){
    	this.height = height;
    }//end setHeight()
    
    /**
     *Returns the object containing infor on the coordinates of this shape 
     *@return The RotatingRect object
     **/
    public RotatingRect getShape(){
    	return shape;
    }//end getShape()
    
    /**
     *Gets the current roation amount
     *@return The amount the object rotates
     **/
    public double getRotation(){
    	return rotation;
    }//end getRotation
    
    /**
     *Asigns a new rotation amount
     *@param rotation The new amount to rotate the object
     **/
    public void setRotation(double rotation){
    	this.rotation = rotation;
    }//end setRotation
    
    /**
     *Returns the center coordinate of the object
     *@return The center coordinate
     **/
    public Point2D.Double getCenter(){
    	Rectangle temp = (shape.getShape()).getBounds();//Get bounds
    	return new Point2D.Double((temp.x+temp.width)/2,(temp.y+temp.height)/2);//Calculate and return center coordinate
    }//end getCenter
    
    /**
     *Checks if the specified points are contained by this object
     *@param x The x coordinate to check
     *@param y The y coordinate to check
     **/
    public boolean contains(double x, double y){
    	return (shape.getShape()).contains(x,y);//Checks if the supplied point is contained 
    }//end contains
    
    /**
	 *Moves the object based only on the current velocity
	 **/
    public void move(){
    	shape.rotate(rotation);
    	location.x += velocity.x;
    	location.y += velocity.y;
    }//end move
    
    /**
     *Tests for intersection between this object and the specified circle
     *@param obj The object to compare against
     **/
    public void intersects(CircularBoundObject obj){
    	/*System.out.println("hello");
    	double dx = (obj.getCenter().x-getCenter().x);   	     
    	double dy = (obj.getCenter().y-getCenter().y);
    	double d = Math.sqrt(((getCenter().x-obj.getCenter().x)*(getCenter().x-obj.getCenter().x))+((getCenter().y-obj.getCenter().y)*(getCenter().y-obj.getCenter().y)));
    	double r = Math.sqrt((dx*dx)+(dy*dy));
    	double diff = r/obj.getRadius();
    	double dxc = dx/diff;
    	double dyc = dy/diff;	
    	if(contains(dxc,dyc)){
    		double vp1 = (velocity.x*dx/d)+(velocity.y*dy/d);					//Velocity in the component dirction of (dx,dy)
    		double vp2 = (obj.getVelocity().x*dx/d)+(obj.getVelocity().y*dy/d);
    		double temp = Math.sqrt((getWidth()*getWidth())*(getHeight()*getHeight()));
    		double dt =(obj.getRadius()+temp-d)/Math.sqrt((vp1-vp2)*(vp1-vp2));
    		getLocation().x-=velocity.x*dt;										//Set positions to the instant of collision 
    		getLocation().y-=velocity.y*dt;
    		obj.setLocation(new Point2D.Double(obj.getLocation().x-(obj.getVelocity().x*dt),obj.getLocation().y-(obj.getVelocity().y*dt)));
    		
    		velocity.x=0;												//Undo projections to get a x/y axes velocity
			velocity.y=0;
			obj.getVelocity().x=0; 
			obj.getVelocity().y=0;
    	}//end if*/
    }//end intersects()
    
    /**
     *Tests for intersection between this object and the specified rectangle
     *@param obj1 The object to compare against
     **/
    public void intersects(RectangularBoundObject obj1){
    	//if(new Rectangle(location.x,location.y,width,height).intersects(new Rectangle(obj1.getLocation().x,obj1.getLocation().y,obj1.getWidth(),obj1.getHeight()))){
    		
    	//}//end if	
    }//end method intersects()
    
}//end class