/**
 * @(#)CircularBoundObject.java
 *
 *
 * @author Mitchell Grenier, Peter Argall, Mark Roukema
 * @version 1.1 2009/12/11
 */

import java.awt.geom.*;
import java.awt.Rectangle;
/**
 *Class that has all the fields and methods pertaining to on screen circular objects
 *@see PhysicsObject
 **/
public class CircularBoundObject extends PhysicsObject{
	private int radius; 			//The radius of the object
	private Point2D.Double center;	//Center coordinates of the object
	
	/**
	 *Creates a new circular bound object with given size
	 *@param weight The weight of the object
	 *@param velocity The velocity of the object
	 *@param quadrent The quadrent the object is located in
	 *@param elasticity The elasticity of the object
	 *@param radius The initial radius of the object
	 *@param location The location of the object
	 *@see PhysicsObject
	 **/
    public CircularBoundObject(int weight, Point2D.Double velocity, int quadrent, double elasticity, int radius, Point2D.Double location) {
    	super(weight, velocity, quadrent, elasticity, location);//call to PhysicsObject constuctor
    	this.radius = radius;
    	this.center = new Point2D.Double(location.x+radius,location.y+radius);
    }//end CircularBoundObject()
    
    /**
     *Blank Constructor
     **/
    public CircularBoundObject(){
    }//end CircularBoundObject()
    
    /**
     *Returns the radius of the object
     *
     *@return The radius of the object
     **/
    public int getRadius(){return radius;}//end getRadius()
    
    /**
     *Set Radius is used to dynamically change the size of the current circular object
     *@param radius The new radius of the circular object
     **/
    public void setRadius(int radius){this.radius = radius;}//end setRadius()
    
    /**
     *Returns the coordinates of the center of this object
     *@return The Point object with the coordinates of the center
     **/
    public Point2D.Double getCenter(){
    	center = new Point2D.Double(location.x+radius,location.y+radius);//Recalculate the center 
    	return center;
    }//end getCenter()
    
    /**
     *Moves this object based on current velocity and watches for intersections with the current bounding box
     *@param bounds The bounding box to base intersections off of
     **/
    public void move(Rectangle bounds){
    	if(getLocation().getX()+velocity.getX()+(getRadius()*2+1)>bounds.width||getLocation().getX()+velocity.getX()<0){
			velocity.x *= -1;//Change direction 
			velocity.x *= elasticity;//Account for lose of speed
		}//end if
		if(getLocation().getY()+velocity.getY()+(getRadius()*2+1)>bounds.height||getLocation().getY()+velocity.getY()<0){
			velocity.y *= -1;//Change direction
			velocity.y *= elasticity;//Account for lose of speed
		}//end if
		
		location.x += velocity.x;//Update location
    	location.y += velocity.y;
    }//end method move()
    
    /**
     *Detects and handles the collision between a circular bound object and a rectagular bound object
     *@param obj the Object to check against
     **/
    public void intersects(RectangularBoundObject obj){
    	/*
    	double dx = (obj.getCenter().x-getCenter().x);   	     
    	double dy = (obj.getCenter().y-getCenter().y);
    	double d = Math.sqrt(((getCenter().x-obj.getCenter().x)*(getCenter().x-obj.getCenter().x))+((getCenter().y-obj.getCenter().y)*(getCenter().y-obj.getCenter().y)));
    	double r = Math.sqrt((dx*dx)+(dy*dy));
    	double diffx = r/getRadius();
    	double diffy = r/getRadius();
    	double dxc = dx/diff;
    	double dyc = dy/diff;	
    	if(obj.contains(getCenter().x+dxc,getCenter().y+dyc)){
    		System.out.println(diff+"  "+dyc*radius);
    		double vp1 = (velocity.x*dx/d)+(velocity.y*dy/d);					//Velocity in the component dirction of (dx,dy)
    		double vp2 = (obj.getVelocity().x*dx/d)+(obj.getVelocity().y*dy/d);
    		double temp = Math.sqrt((obj.getWidth()*obj.getWidth())*(obj.getHeight()*obj.getHeight()));
    		double dt =(radius+temp-d)/Math.sqrt((vp1-vp2)*(vp1-vp2));
    		getLocation().x-=velocity.x*dt;										//Set positions to the instant of collision 
    		getLocation().y-=velocity.y*dt;
    		obj.setLocation(new Point2D.Double(obj.getLocation().x-(obj.getVelocity().x*dt),obj.getLocation().y-(obj.getVelocity().y*dt)));
    		
    		velocity.x=0;												//Undo projections to get a x/y axes velocity
			velocity.y=0;
			obj.getVelocity().x=0; 
			obj.getVelocity().y=0;
    	}//end if
    	*/
    }//end intersects()
    
    /**
     *Checks if this object collides with the supplied object
     *@param obj The object to check against
     *@return true/false based of if the two objects collide with eachother
     **/
    public boolean doesCollide(CircularBoundObject obj){
    	double d = Math.sqrt(((getCenter().x-obj.getCenter().x)*(getCenter().x-obj.getCenter().x))+((getCenter().y-obj.getCenter().y)*(getCenter().y-obj.getCenter().y)));
    	return d<(radius+obj.radius);
    }//end method doesCollide()
    
    /**
     *Detects and handles the collision between two circular bound objects
     *@param obj the Object to check against
     **/
    public void intersects(CircularBoundObject obj){
    	//Calculate the distance between the two circles
    	double d = Math.sqrt(((getCenter().x-obj.getCenter().x)*(getCenter().x-obj.getCenter().x))+((getCenter().y-obj.getCenter().y)*(getCenter().y-obj.getCenter().y)));
    	if(d<(radius+obj.radius)){											//If the distance is less then the sum of the radii then they are intersecting
    		double dx = obj.getCenter().x-getCenter().x;							//X component of the line between the two objects
    		double dy = obj.getCenter().y-getCenter().y;							//Y component of the line between the two balls								
    		
    		double vp1 = (velocity.x*dx/d)+(velocity.y*dy/d);					//Velocity in the component dirction of (dx,dy)
    		double vp2 = (obj.getVelocity().x*dx/d)+(obj.getVelocity().y*dy/d);
    		
    		double dt =(radius+obj.getRadius()-d)/Math.sqrt((vp1-vp2)*(vp1-vp2));				//Calculate time before this instant the collision happened
    		
    		//System.out.println(dt);
    		getLocation().x-=velocity.x*dt;										//Set positions to the instant of collision 
    		getLocation().y-=velocity.y*dt;
    		obj.setLocation(new Point2D.Double(obj.getLocation().x-(obj.getVelocity().x*dt),obj.getLocation().y-(obj.getVelocity().y*dt)));
    		
    		dx = obj.getCenter().x-getCenter().x;								//Re-calculate slopes
    		dy = obj.getCenter().y-getCenter().y;
    		if(dy<0){
    			dx*=-1;															//If both vectors are negative then it is equivelent 
    			dy*=-1;															//If only Y is negative then for logistic purposes keeping x negative is equivelent
    		}//end if
    	
    		
			//Recalculate distance
			d = Math.sqrt(((getCenter().x-obj.getCenter().x)*(getCenter().x-obj.getCenter().x))+((getCenter().y-obj.getCenter().y)*(getCenter().y-obj.getCenter().y)));
			
    		double ax=dx/d;														//Unit vectors in the direction of the collision
    		double ay=d/dy; 
    		
    		double nax = dy;													//Take the negative reciprocal of the collision direction
    		double nay = dx*-1;
    		
    		double temp = ((velocity.x*nax+velocity.y*nay)/((nax*nax)+(nay*nay)));
    		double cx1 =  (temp*nax);											//Create a vector in the collision direction proportional to the velocity
    		double cy1 =  (temp*nay);
    		
    		temp = 			((obj.getVelocity().x*nax+obj.getVelocity().y*nay)/((nax*nax)+(nay*nay)));
    		double cx2 =  	(temp*nax);
    		double cy2 =  	(temp*nay);
		
    		double resultantX1 = cx1-(velocity.x-cx1);							//Resultant vectors
    		double resultantY1 = cy1-(velocity.y-cy1);
    		double resultantX2 = cx2-(obj.getVelocity().x-cx2);
    		double resultantY2 = cy2-(obj.getVelocity().y-cy2);
    		
    		velocity.x=(elasticity)*resultantX1;												//Undo projections to get a x/y axes velocity
			velocity.y=(elasticity)*resultantY1;
			//velocity.y-=accel;
			//accel=0.0;
			obj.getVelocity().x=(obj.getElasticity())*resultantX2; 
			obj.getVelocity().y=(obj.getElasticity())*resultantY2;
			//obj.velocity.y-=obj.accel;
  			//obj.setAccel(0.0);
  			
			getLocation().x+=velocity.x*dt;										//Move positions foward by the same abount of time they were moved back
  			getLocation().y+=velocity.y*dt;
  			obj.getLocation().x+=obj.getVelocity().x*dt;
  			obj.getLocation().y+=obj.getVelocity().y*dt;

    	}//end if	
    }//end method intersects()
    
}//end class