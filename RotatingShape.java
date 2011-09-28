/**
 * @(#)RotatingShape.java
 *
 *
 * @author Peter and Mitchell
 * @version 1.02 2010/1/5
 */

import java.awt.*;
/**
 *Holds a polygon and has methods allowing for the rotation of a rectangle
 **/
public class RotatingShape {

	protected Polygon shape = new Polygon();//The shape with the center at 0,0
	protected int x=0, y=0;					//Center points

	/**
	 *Blank contructor to create object
	 */
    public RotatingShape() {
    }//end constructor
    
    /**
     *Changes the center coordinates
     *
     *@param x The new x value
     *@param y The new y value
     */
    public void setCenter(int x, int y){
		this.x = x;
		this.y = y;
	}//end method setCenter()
   
    /**
     *Allows the shape to be accessed
     *
     *@return The shape made in this method
     */
     public Polygon getShape(){
    	int newX =0, newY=0;										//Temp variables
    	Polygon movedShape = new Polygon();							//Creates a new polygon to work with
    	for(int i=0; i<shape.npoints; i++){							//Moves the polygon to the desired place on the canvas
    		newX = shape.xpoints[i] + x;							//Moves X Co-ordinates
    		newY = shape.ypoints[i] + y;							//Moves Y Co-ordinates
    		movedShape.addPoint((int)newX,(int)newY);				//Adds Points to the Polygon
    	}//end for
    	return movedShape;											//Return the moved polygon
    }//end getShape
    
    /**
     *Returns the Polygon used to store the coordinates of the shape
     *
     *@return The stored Polygon
     **/
    public Polygon getPolygon(){
    	return shape;
    }//end getPolygon
    
    /**
     *Rotates this shape theta radians
     *
     *@param theta Number of radians to rotate
     */
    public void rotate(double theta) {
		Polygon newPoly = new Polygon();							//Create a New Polygon
    	double x=0, y=0;											//Temp variables for the shape it creates
		double cos_t=Math.cos(theta);								//Cosine of the angle it's rotating by
		double sin_t=Math.sin(theta);  								//Sine of the angle it's rotating by
		for (int i=0; i<shape.npoints; i++) {						//Calculate each points new position
			x =  shape.xpoints[i]*cos_t  + shape.ypoints[i]*sin_t;	//X Co-ordinate
			y = -shape.xpoints[i]*sin_t  + shape.ypoints[i]*cos_t;	//Y Co-ordinate
			newPoly.addPoint((int)Math.round(x),(int)Math.round(y));//Add point to the polygon
		}//end for
		shape = newPoly;											//Overwrites the old polygon with the rotated one
	}//end method rotate()
	
		/*REMOVE THIS METHOD BEFORE FINAL SUBMISSION*/
	public void rotate(double theta, boolean epic) {
		Polygon newPoly = new Polygon();							//Create a New Polygon
    	double x=0, y=0;											//Temp variables for the shape it creates
		double cos_t=Math.cos(theta);								//Cosine of the angle it's rotating by
		double sin_t=Math.sin(theta);  								//Sine of the angle it's rotating by
		for (int i=0; i<shape.npoints; i++) {						//Calculate each points new position
			x =  shape.xpoints[i]*cos_t  + shape.ypoints[i]*sin_t;	//X Co-ordinate
			y = -shape.xpoints[i]*sin_t  + shape.ypoints[i]*cos_t;	//Y Co-ordinate
			newPoly.addPoint((int)x,(int)y);						//Add point to the polygon
		}
		shape = newPoly;											//Overwrites the old polygon with the rotated one
	}
	
    
}