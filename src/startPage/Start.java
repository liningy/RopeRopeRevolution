package startPage;

import processing.core.*;
import kiteFlying.Kite;
import menuSelection.MenuSelection;
import wiigeeRope.WiiManager;

public class Start extends PApplet  {
	private static final long serialVersionUID = 1L;
	
	public MenuSelection menuSelection;
	public Kite kite1;
	public PImage bg;
	
	public WiiManager wiiManager;
    
	public boolean startMode,kiteMode,horseMode,jumpMode;
	public int startPointX, startPointY;
	
	
	public void setup(){
		size(1024,768);
		background(0);
		smooth();
		
		bg=loadImage("bg.png");
		startPointX=0;
		startPointY=0;
		
		kite1= new Kite (this);
		menuSelection = new MenuSelection (this);
		
		startMode();

		//wiiManager=new WiiManager();
	}
	
	public void draw() {
		image(bg,startPointX,startPointY);
		
		if(startMode){
			bgMoving(0,0);
			menuSelection.display();
			
//			if(wiiManager.ropeGestureListener.gestureID == 1){
//				kiteMode();
//				wiiManager.ropeGestureListener.gestureID =0;
//			} 
//			else if (wiiManager.ropeGestureListener.gestureID == 2){
//				horseMode();
//				wiiManager.ropeGestureListener.gestureID =0;
//			}
//			else if(wiiManager.ropeGestureListener.gestureID == 3){
//				jumpMode();
//				wiiManager.ropeGestureListener.gestureID =0;
//			}
		}

		if(kiteMode){
			bgMoving(-800,0);

			if(kite1.noWind){
				kite1.withoutWind();
			}
			if(kite1.rightWind){
				kite1.withRightWind();
			}
			if(kite1.leftWind){
				kite1.withLeftWind();
			}
			kite1.writeScoreForKite1();	
		}
		
		if(horseMode){
			bgMoving(20,-768);
		}
		if(jumpMode){
			bgMoving(-1024,-768);
		}
	}
	
	public void keyPressed() {
		//SWITCHING MODE
		if(key=='4') startMode();
		if(key=='1') kiteMode();
		if(key=='2') horseMode();
		if(key=='3') jumpMode();
		
		//KITE FLYING
		if(keyCode==UP){
			kite1.goUpStatus();
		}

		if(keyCode==DOWN){
			kite1.goDownStatus();
		}
		if(keyCode==LEFT){
			kite1.goLeftStatus();
		}
		if(keyCode==RIGHT){
			kite1.goRightStatus();
		}	
	}
	
	//MODE SWITCHING FUNCTION
	public void startMode(){
		startMode=true;
		kiteMode=false;
		horseMode=false;
		jumpMode=false;
	}
	public void kiteMode(){
		startMode=false;
		kiteMode=true;
		horseMode=false;
		jumpMode=false;
	}
	public void horseMode(){
		startMode=false;
		kiteMode=false;
		horseMode=true;
		jumpMode=false;
	}
	public void jumpMode(){
		startMode=false;
		kiteMode=false;
		horseMode=false;
		jumpMode=true;
	}
	public void bgMoving(int xPosition, int yPosition){
	    if(startPointX>xPosition) startPointX-=8;
	    if(startPointX<xPosition) startPointX+=8;
	    if(startPointY>yPosition) startPointY-=8;
	    if(startPointY<yPosition) startPointY+=8;
	}
	
	
	public static void main(String args[]) {
		PApplet.main(new String[] {"--present", "startPage.Start"});
	}
}


