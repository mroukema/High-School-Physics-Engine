/**
 * @(#)PhysicsObject.java
 *
 *
 * @author Mitchell Grenier, Mark Roukema
 * @version 1.07 2009/12/3
 */
import java.awt.Point.*;
import java.awt.geom.*;

/**
 *The basic template for a Physics object
 *@see PhysicsObjectInterface
 **/
public abstract class PhysicsObject implements PhysicsObjectInterface{
	Point2D.Double location;//The location of the object
	int weight; //The weight of the object
	Point2D.Double velocity; //The x and y co-ordinates can be stored in this variable
	int quadrent; //The quadrent the object is in.
	double elasticity; //How much energy is retained in a collions
	final double GRAVITYCONSTANT = 3;//Gravitational Constant determins how string the effect of gravity is
	
	/**
	 *Create a new object with given values
	 *
	 *@param weight The weight of the object
	 *@param velocity The x and y co-ordinates of the object
	 *@param quadrent The quardrent the the object is currently in
	 *@param elasticity The amount of retained energy ina collision
	 **/
    public PhysicsObject(int weight, Point2D.Double velocity, int quadrent, double elasticity, Point2D.Double location) {
    	this.weight = weight;
    	this.velocity = velocity;
    	this.quadrent = quadrent;
    	this.elasticity = elasticity;
    	this.location = location;
    }//end constructor 
    
    /**
     *Blank Constructor
     **/
    public PhysicsObject(){}
    
    /**
     *Sets the location of this object
     *@param location The new location
     **/
    public void setLocation(Point2D.Double location){this.location = location;}
    
    /**
     *Sets the elasticity of this object
     *@param elasticity The new elasticity for this object
     **/
    public void setElasticity(double elasticity){this.elasticity = elasticity;}
    
    /**
     *Sets the weight of this object
     *@param weight The new weight for this object
     **/
    public void setWeight(int weight){this.weight = weight;}
    
    /**
     *Returns the current location of this object
     *@return The location variable
     **/
    public Point2D.Double getLocation(){return location;}
    
    /**
     *Gets the velocity of this object
     *@return The current velocity
     **/
    public Point2D.Double getVelocity(){return velocity;}
    
    /**
     *Gets the elasticity of this object
     *@return the elacticity variable
     **/
    public double getElasticity(){return elasticity;}
    
    /**
     *Gets the weight of this object
     *@return The current weight
     **/
    public int getWeight(){return weight;}
    
    /**
     *Applies the effect of gravity to this object based on the programs gravitational constant and the worlds gravity modifier
     *@param grav The worlds current gravity modifer
     **/
    public void applyGravity(double grav){
    	velocity.y += GRAVITYCONSTANT*grav; //Increase velocity by the effect of gravity
    }//end applyGravity
     
	/**
	 *Moves the object based only on the current velocity
	 **/
    public void move(){
    	location.x += velocity.x;
    	location.y += velocity.y;
    }//end move
    
    
}//end class