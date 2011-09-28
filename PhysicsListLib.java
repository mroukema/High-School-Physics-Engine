/**
 * @(#)PhysicsListLib.java
 *
 *
 * @author 
 * @version 1.00 2009/12/7
 */

/**
 *A Library for accessing a linked list
 **/
public class PhysicsListLib {
	
	/**
	 *Constructor
	 **/
    public PhysicsListLib() {
    }//end constructor
    
    /**
     *Method adds the specified object to the linked list
     *@param root The current root of this list
     *@param nextObject The object to add to the lnked list
     **/
    public void add(PhysicsListNode root, PhysicsObject nextObject){
		if(root.next != null)
			add(root.next, nextObject);
		else
			root.next = new PhysicsListNode(nextObject);
    }//end method add()   
    
    /**
     *Methods removes the specified node from the list
     *@param root The current root of this list
     *@param toRemove The node to be removed
     **/
    public void remove(PhysicsListNode root, PhysicsListNode toRemove){
    	if(!(root.next.equals(toRemove))){
    		remove(root.next,toRemove);
    	}//end if
    	else
    		root.next = root.next.next;
    }//end remove()
}//end class