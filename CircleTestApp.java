/**
 * @(#)TestApp.java
 *
 *
 * @author 
 * @version 1.00 2010/1/6 
 */

import java.awt.*;
import java.awt.geom.*;

public class CircleTestApp extends java.applet.Applet implements Runnable{
    private Engine physics;	//The Engine object that will be used to handle physics and collision calculations
    private Graphics dbg;	//for double buffering
    private Image dbImage;	//for double buffering
    private int width =500;	//width of this program
    private int height =500;//height of this program
    
    /**
     *Blnak Constructor
     **/
    public CircleTestApp(){
    }//end constructor
     
    /** 
     *Initialization method that will be called after the applet is loaded into the browser.
     */
    public void init() {
    	width = getSize().width;//Set wdth and height based on valuse it was initialized with
    	height = getSize().height;
    	
        physics = new Engine(new World(.5,0,new Rectangle(0,0,width,height)));//Create the engine
        
        doubleBufferSetup();
        
		addRandomCircle();//Objects program starts with 
		addRandomCircle();
		addRandomCircle();
		
    	new Thread(this).start();//Starts the program
    }//end init()
	
	/*
	 *Prepares the double buffering related objects for use
	 */
	 public void doubleBufferSetup(){
   		dbImage = createImage(getSize().width,getSize().height);//creates an image based on the width/height of the JAppelt	
    	dbg = dbImage.getGraphics();//Creates a graphics object based on the Image just created
	 }//end doubleBufferSetup
	 
	 /**
	  *The method run by this program when threaded. It handles drawing and calls to the physics engine
	  **/
	public void run(){
		while(true){
			physics.doPhysics();
			repaint();
			try{Thread.sleep(20);}
    		catch(InterruptedException e){}
		}//end while()
	}//end run
	
	/**
	 *Method draws an oval based on the supplied oval object
	 *@param oval The object to draw
	 **/
	public void paintOval(CircularBoundObject oval){
		dbg.setColor(Color.blue);
		dbg.fillOval((int)oval.getLocation().getX(),(int)oval.getLocation().getY(),oval.getRadius()*2,oval.getRadius()*2);
	}//end paintOval()
	
	/*
	 *Update method is called by repaint() it prevents the screen from wiping before a new image has been prepared
	 *@param g The graphics object of the program
	 */
	public void update(Graphics g){
		paint(g);
	}//end update
	
	/**
	 *Generates a number between the two supplied values and not zero
	 *@param lower The lowest possible value to be generated
	 *@param upper The highest possible value to be generated
	 *@return The random number generated
	 **/
	public double randomGen(double lower, double upper){
   		double num = 0;
   		while(num == 0){
   			num = (Math.random()*(upper-lower) + lower);
   		}//end while
		return num;
	}//end randomGen
	
	/**
	 *Adds a circle in a rndom position with random values
	 **/
	public void addRandomCircle(){
		boolean collide = true;
		CircularBoundObject newObj = new CircularBoundObject();
		while(collide == true){//don't add the object if it will be inside another object
			newObj = new CircularBoundObject(	1,//weight
												new Point2D.Double(randomGen(-2.5,2.5),randomGen(-2.5,2.5)),//velocity
												1,//quadrent(unused at the moment)
												.6,//Elasticity
												(int)randomGen(15,25),//size
												new Point2D.Double(randomGen(50,450),randomGen(50,450)));//location
			PhysicsListNode root = physics.getList();
			collide=false;//Assume not colliding until proven otherwise
			for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
				CircularBoundObject check = (CircularBoundObject)temp.getOurObject(); 
				if(check.doesCollide(newObj)){
					collide = true;
				}//end if
			}//end for
		}//end while
		physics.addCircularObject(newObj);//Add object to list
	}//end method addCircle()
	
	/**
	 *Gets The current physics engine used by the program
	 *@return The Engine object
	 **/
	public Engine getEngine(){
		return physics;
	}//end getEngine()
	
	/**
	 * Method removes the object that was last added to the linked list
	 **/
	public void removeLatest(){
		PhysicsListNode root = physics.getList();
		PhysicsListNode toRemove = findLatest();//returns last added object
		if(!(toRemove.equals(root))){//don't remove the root
			PhysicsListLib temp = new PhysicsListLib();
			temp.remove(root,toRemove);
		}//end if 	
	}//end removeLatest()
	
	/**
	 *Finds and returns the last object in the linked list
	 *@return The last PhysicsListNode contained in the list
	 **/
	public PhysicsListNode findLatest(){
		PhysicsListNode root = physics.getList();
		for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
			if(temp.getNext()==null)
				return temp;
		}//end for
		return null;//should never be used	
	}//end findLatest()
		
	/**
	 *The Paint method called by the compiler. Handles basic setup and calls to other painting methods
	 *@param g The graphics object of the program
	 **/
    public void paint(Graphics g) {
    	//*******Activating Anti Aliasing*******
		((Graphics2D)g).setRenderingHint			
  		(RenderingHints.KEY_ANTIALIASING,
   		RenderingHints.VALUE_ANTIALIAS_ON);
   		//*******End of Anti Aliasing activation code*******
   		
    	dbg.setColor(Color.black);//background painting
    	dbg.fillRect(0,0,width,height);
    	
        PhysicsListNode root = physics.getList();//get access to linked list
        
        for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
        	if(temp.getOurObject() instanceof CircularBoundObject)//different methods for different objects
				paintOval((CircularBoundObject)temp.getOurObject());
			else
				dbg.fillPolygon(((RectangularBoundObject)temp.getOurObject()).getShape().getShape());
		}//end for
		
		g.drawImage(dbImage,0,0,this);//paint entire screen at once	
    }//end paint
    
}//end class