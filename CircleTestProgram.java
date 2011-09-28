/**
 * Mark Roukema
 *
 * 12 October 2009
 * 
 * Host JApplet
 *
 * This program acts as a frame for the applet. It also hosts the Add and Remove thread Buttons whih trigger the addition 
 *or removal of threads by calling a method located within the Main Applet class 
 */

import java.awt.*;
import java.applet.*; 
import javax.swing.*; 
import java.awt.event.*; 

public class CircleTestProgram extends JFrame implements ActionListener{
	CircleTestApp thisAp;
	boolean started = false;
    public CircleTestProgram(){
    	setSize(500,558);
    	thisAp = new CircleTestApp();//Creates an blank Test object 
    	
    	Button northButton = new Button("Add Circle");
    	northButton.addActionListener(this);//Creates a Add Thread button
    	
    	Button southButton = new Button("Remove Circle");
    	southButton.addActionListener(this);//Creates a Revove Thread button
    	
	
    	add(northButton,BorderLayout.NORTH);//adds the Add Thread Button to the North Position
    	add(southButton,BorderLayout.SOUTH);//adds the Remove Thread Button to the South Position
    	
		
		
		// Sets the size of the JApplet based on the area not filled by the buttons
    	thisAp.setSize(500,500);
    	//runs the init method of the JApplet, effectivly starting the program
    	
    	
       	//Sets the bounds of the JApplet for convience
    	thisAp.setBounds(0,0,thisAp.getSize().width,thisAp.getSize().height);
    	
    }//end init
    
    public void actionPerformed(ActionEvent evt){
    	String buttonPressed = evt.getActionCommand();
    	if(buttonPressed == "Add Circle"){
    		if(!started){
    			add(thisAp,BorderLayout.CENTER);//Adds the JApplet to the center position
    			thisAp.setVisible(true);
    			thisAp.init();
    			started = true;
    		}//end if 
    		else
    			thisAp.addRandomCircle();
    	}//end if
    	
    	if(buttonPressed == "Remove Circle"){
    		if(started)
    			thisAp.removeLatest();
    	}//end if	
    }//end actionPreformed
    
    public void paint(Graphics g){
    }//end paint
    
    public static void main(String[] args){
    	CircleTestProgram test = new CircleTestProgram();
    	
    	test.setVisible(true);
    }//end main()
    
}