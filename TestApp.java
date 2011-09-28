/**
 * @(#)TestApp.java
 *
 *
 * @author 
 * @version 1.00 2010/1/6
 */

import java.awt.*;
import java.awt.geom.*;

public class TestApp extends java.applet.Applet implements Runnable{
    Engine physics;
    Graphics dbg;
    Image dbImage;
    int width =500;
    int height =500;
    
    /** 
     *Initialization method that will be called after the applet is loaded into the browser.
     */
    public void init() {
        physics = new Engine(new World(0,0,new Rectangle(0,0,width,height)));
        
        doubleBufferSetup();
        
        physics.addCircularObject(new CircularBoundObject(1/*weight*/,new Point2D.Double(-3,-1),1,1.0,20,new Point2D.Double(100,100)));
        physics.addCircularObject(new CircularBoundObject(1/*weight*/,new Point2D.Double(-2,1.24),1,1.0,20,new Point2D.Double(200,143)));

    	new Thread(this).start();
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
   		
    	dbg.setColor(Color.black);
    	dbg.fillRect(0,0,width,height);
    	
        PhysicsListNode root = physics.getList();
        
        for(PhysicsListNode temp= root; temp!=null;temp=temp.getNext()){
        	if(temp.getOurObject() instanceof CircularBoundObject)
				paintOval((CircularBoundObject)temp.getOurObject());
			else
				dbg.fillPolygon(((RectangularBoundObject)temp.getOurObject()).getShape().getShape());
		}//end for
		
		g.drawImage(dbImage,0,0,this);	
    }//end paint
    
}//end class