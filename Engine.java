 /**
 * @author Peter Argall
 * @version 1.01 2009/12/3
 */

/**
 *This class is the users link to the internal funtions of the physics engine. The movement and collision detecions functions can be accessed through these objects. In addition object managment can also be handled
 *@see RectangularBoundObject
 *@see CircularBoundObject
 *@see World
 *@see PhysicsListLib
 *@see PhysicsListNode
 **/
public class Engine {
	private World world;			//Contains information that effects the operating space as a whole
	private PhysicsListNode root;	//Root of the list containing PhysicsObjects
	
	/**
	 *Blank Constructor
	 **/
	public Engine(){}
	
	/**
	 *Creates an engine with a specified World object
	 *@param world The world object that this object contains
	 *@see World
	 **/
	public Engine(World world){
		this.world = world;
	}//end constructor
	
	/**
	 *Creates a Engine object with a single object pre-initialized
	 *@param createObject The object the is to be created and added to the engines workspace
	 *@see PhysicsObject
	 **/
	public Engine(PhysicsObject createObject){
		root = new PhysicsListNode(createObject);
	}//end constructor
    
    /**
     *Creates a Engine object with the specified initial Object and World object
     *@param createObject The object the is to be created and added to the engines workspace
     *@param world The world object that this object contains 
     *@see PhysicsObject
     *@see World
     **/
    public Engine(PhysicsObject createObject, World world){
    	root = new PhysicsListNode(createObject);
		this.world = world;
	}//end constructor
	
    /**
     *Method returns the World object contained in this class
     *@return The world Object
     *@see World
     **/
    public World getWorld(){
    	return world;
    }//end method getWorld()
    
    /**
     *Assigns a new world object to this class
     *@param world The new world object to use
     *@see World
     **/
    public void setWorld(World world){
    	this.world = world;
    }//end setWorld
    
    /**
     *Returns the root of the linked list so that the contained elements can be accesed 
     *@return The root of the linked list
     *@see PhysicsListNode
     *@see PhysicsListLib
     **/
    public PhysicsListNode getList(){return root;}//end getList() 
    	
    /**
     *Handles all physics related calculations 
     **/	
	public void doPhysics(){
		applyGravity();//Apply the effect of gravity
		moveObjects();//Movement of objects step in the program
		doCollisions();//collision detection and handeling step
	}//end doPhysics()
	
	/**
	 *Applies the effect of gravity to all of the objects contained in the linked list
	 **/
	public void applyGravity(){
		for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
			temp.getOurObject().applyGravity(world.getGravity());//Uses the PhysicsObjects applyGravity method and supplies the gravity modifier contained in the world class
		}//end for
	}//end method applyGravity()
	/**
	 *Moves all objects 
	 **/
	public void moveObjects(){
		for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
			if(temp.getOurObject() instanceof CircularBoundObject){//Check if this object is a circle
				CircularBoundObject temp2 = (CircularBoundObject)temp.getOurObject();//Cast to change type
				temp2.move(world.getBounds());//if so use the circular version of move object
			}//end if 
			if(temp.getOurObject() instanceof RectangularBoundObject){//check if this object is a rectangle
				RectangularBoundObject temp2 = (RectangularBoundObject)temp.getOurObject();
				temp2.move();
			}//end if
		}//end for
	}//end moveObjects()
	
	/**
	 *Method handles the testing for collisions 
	 **/
	public void doCollisions(){
		for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
			for(PhysicsListNode temp1= temp.getNext(); temp1!=null;temp1=temp1.getNext()){//Double for loop ensures every combination is tried but permeatations are not
				if(temp1.getOurObject() instanceof CircularBoundObject){//Check if this object is a circle
					temp.getOurObject().intersects((CircularBoundObject)(temp1.getOurObject()));
				}//end if 
				if(temp1.getOurObject() instanceof RectangularBoundObject){
					temp.getOurObject().intersects((RectangularBoundObject)(temp1.getOurObject()));
				}//end if 
			}//end for
		}//end for
	}//end doCollisions()
	
	/**
	 *Method adds the specified object to the engine's list of objects
	 *@param createObject The object to be added
	 *@see RectangularBoundObject  
	 **/
	public void addRectangularObject(RectangularBoundObject createObject){
		if(root == null)
			root = new PhysicsListNode(createObject);
		else{
			PhysicsListLib temp = new PhysicsListLib();
			temp.add(root,createObject);
		}//end else
	}//end method addRectangularBoundObject
	
	/**
	 *Adds the specified CircularBoundObject to the engine's object list so that it can be worked with
	 *@param createObject The object to be added
	 *@see CircularBoundObject 
	 **/
	public void addCircularObject(CircularBoundObject createObject){
		if(root == null)
			root = new PhysicsListNode(createObject);
		else{
			PhysicsListLib temp = new PhysicsListLib();
			temp.add(root,createObject);
		}//end else
	}//end method addCircularBoundObject
}//end class 