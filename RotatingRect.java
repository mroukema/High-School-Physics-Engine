/**
 * @(#)rotatingRect.java
 *
 *
 * @author Mark Roukema
 * @version 1.03 2010/1/5
 */


import java.awt.*;
/**
 *Creates a rectangular rotating shape
 *@see RotatingShape
 **/
public class RotatingRect extends RotatingShape{
	
	/*Creates a rotatable rectangle
	 *
	 *@param width the width of the rectangle
	 *@param height the height of the rectangle
	 *@param centerX the center x point
	 *@param centerY the center y point
	 */
    public RotatingRect(int width, int height, int centerX, int centerY){
    	this.x = centerX;
    	this.y = centerY;
   		shape.addPoint(-width/2,-height/2);//top left
   		shape.addPoint(width/2,-height/2);//top Right
    	shape.addPoint(width/2,height/2); //bottom right
    	shape.addPoint(-width/2,height/2);//bottom left
    }//end method RotatingRect()
    
}//end class