/**
 * @(#)PhysicsObjectInterface.java
 *
 *
 * @author Mark Roukema
 * @version 1.01 2009/12/3
 */

/**
 *The basic nessesary structure for the physics object
 **/
public interface PhysicsObjectInterface {
	
    /**
     *Method for handling intersections with rectangular objects
     *@param obj1 The rectanular object
     **/
    public void intersects(RectangularBoundObject obj1);
    
    /**
     *Method for handling intersections with circular objects
     *@param obj The circular object
     **/
    public void intersects(CircularBoundObject obj);
    
    /**
     *Method for moving objects
     **/
    public void move();
    
}//end class