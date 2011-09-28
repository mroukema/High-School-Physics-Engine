/**
 * @(#)TestApp.java
 *
 *
 * @author 
 * @version 1.00 2010/1/6
 */

import java.awt.*;
import java.awt.geom.*;

/**
 *Rectangle demonstration
 **/
public class RectangleDemo extends java.applet.Applet implements Runnable{
    private Engine physics;	//The Engine object that will be used to handle physics and collision calculations
    private Graphics dbg;	//for double buffering
    private Image dbImage;	//for double buffering
    private int width =500;	//width of this program
    private int height =500;//height of this program
    
    /** 
     *Initialization method that will be called after the applet is loaded into the browser.
     */
    public void init() {
    	
    	width = getSize().width;//Set wdth and height based on valuse it was initialized with
    	height = getSize().height;
    	
        physics = new Engine(new World(0,0,new Rectangle(0,0,width,height)));//Create the engine
        doubleBufferSetup();
        
        physics.addRectangularObject(new RectangularBoundObject(30/*width*/,
        														40/*height*/,
        														1/*weight*/,
        														new Point2D.Double(0,0)/*Velocity*/,
        														1/*quadrent*/,
        														1/*elasticity*/,
        														new Point2D.Double(100,100)/*Location*/,
        														-0.15/*Rotation*/));
        														
        physics.addRectangularObject(new RectangularBoundObject(60/*width*/,
        														60/*height*/,
        														1/*weight*/,
        														new Point2D.Double(0,0)/*Velocity*/,
        														1/*quadrent*/,
        														1/*elasticity*/,
        														new Point2D.Double(300,300)/*Location*/,
        														0.3/*Ratation*/));
        														
        physics.addRectangularObject(new RectangularBoundObject(60/*width*/,
        														60/*height*/,
        														1/*weight*/,
        														new Point2D.Double(0,0)/*Velocity*/,
        														1/*quadrent*/,
        														1/*elasticity*/,
        														new Point2D.Double(100,300)/*Location*/,
        														0.5/*Ratation*/));	
        															
        physics.addRectangularObject(new RectangularBoundObject(60/*width*/,
        														60/*height*/,
        														1/*weight*/,
        														new Point2D.Double(0,0)/*Velocity*/,
        														1/*quadrent*/,
        														1/*elasticity*/,
        														new Point2D.Double(300,100)/*Location*/,
        														-0.05/*Ratation*/));																															
		
    	new Thread(this).start();//Start The program
    }//end init()
	
	/*
	 *Prepares the double buffering related objects for use
	 */
	 public void doubleBufferSetup(){
   		dbImage = createImage(width,height);//creates an image based on the width/height of the JAppelt	
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
			else{
				dbg.setColor(Color.blue);
				dbg.fillPolygon(((RectangularBoundObject)temp.getOurObject()).getShape().getShape());
			}//end else
		}//end for
		
		g.drawImage(dbImage,0,0,this);//paint entire screen at once		
    }//end paint
    
}//end class