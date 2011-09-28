/**
 * @(#)physicsListNode.java
 *
 *
 * @author Peter Argall, Mark ROukema
 * @version 1.04 2009/12/4
 */

/**
 *A node in a linked list that contains a single phyiscs object
 *@see PhysicsListLib
 **/
public class PhysicsListNode {
	PhysicsListNode next;	//The pointer to the next file
	private PhysicsObject ourObject;//The object stored here
	
   /** 
    * The Constructor
	* @param ourObject the PhysicsObject to save in this node
	**/
    public PhysicsListNode(PhysicsObject ourObject) {
    	this.ourObject = ourObject;
    }//end constructor
    
   /**
    *Sets the pointer to a new object
    * 
    *@param next The new PhysicsObject to link to
    **/
    public void setNext(PhysicsObject next){
    	this.next = new PhysicsListNode(next);
    }//end ,ethod setNext()
    
    /**
     *Method Returns the next object in this list
     *@return The next object in the list
     **/
    public PhysicsListNode getNext(){
    	return next;
    }//end method getNext()
    
    /**
     *Returns the PysicsObject contained in this node
     *@return The object contained in this node
     **/
    public PhysicsObject getOurObject(){
    	return ourObject;
    }//end getOurObject()
}