package startPage;

import horseDriving.Horse;
import processing.core.*;
import kiteFlying.Kite;
import kiteFlying.KiteTest;
import menuSelection.MenuSelection;
import wiigeeRope.WiiManager;

public class Start extends PApplet  {
	private static final long serialVersionUID = 1L;
	
	public MenuSelection menuSelection;
	public Kite kite;
	public Horse horse;
	public KiteTest kiteTest;
	public PImage bg;
	
	public WiiManager wiiManager;
    
	public boolean startMode,kiteMode,horseMode,jumpMode,sawMode;
	public int startPointX, startPointY;
	
	
	public void setup(){
		size(1024,768);
		background(0);
		smooth();
		
		bg=loadImage("bg.png");
		startPointX=0;
		startPointY=0;
		
		
		menuSelection = new MenuSelection (this);
		
		startMode();

		//WIIGEE
		wiiManager=new WiiManager();
	}
	
	public void draw() {
		image(bg,startPointX,startPointY);
		
		if(startMode){
			bgMoving(0,0);
			menuSelection.display();
			
			//WIIGII_Gesture Menu Selection
			if(wiiManager.ropeGestureListener.gestureID == 1){
				kiteMode();
				kite= new Kite (this);
				wiiManager.ropeGestureListener.gestureID =0;
			} 
			else if (wiiManager.ropeGestureListener.gestureID == 2){
				horseMode();
				horse=new Horse(this);
				wiiManager.ropeGestureListener.gestureID =0;
			}
			else if(wiiManager.ropeGestureListener.gestureID == 3){
				jumpMode();
				wiiManager.ropeGestureListener.gestureID =0;
			}
			else if(wiiManager.ropeGestureListener.gestureID == 4){
				sawMode();
				wiiManager.ropeGestureListener.gestureID =0;
			}
		}

		if(kiteMode){
			bgMoving(-800,0);

			if(kite.noWind){
				kite.withoutWind();
			}
			if(kite.rightWind){
				kite.withRightWind();
			}
			if(kite.leftWind){
				kite.withLeftWind();
			}
			kite.writeScoreForKite1();	
		}
		
		if(horseMode){
			bgMoving(0,-768);
			horse.horseDriving();
		}
		if(jumpMode){
			bgMoving(-1024,-768);
		}
		if(sawMode){
			bgMoving(-2040,-768);
		}
	}
	
	public void keyPressed() {
		//SWITCHING MODE
		if(key=='0') startMode();
		if(key=='1') {
			kiteMode();
			kite= new Kite (this);
		}
		if(key=='2') {
			horseMode();
			//horse= new Horse (this);

		}
		if(key=='3') jumpMode();
		if(key=='4') sawMode();
		
		//KITE FLYING
		if(kiteMode){
			if(keyCode==UP){
				kite.goUpStatus();
			}

			if(keyCode==DOWN){
				kite.goDownStatus();
			}
			if(keyCode==LEFT){
				kite.goLeftStatus();
			}
			if(keyCode==RIGHT){
				kite.goRightStatus();
			}	
		}
		
		//HORSE DRIVING
		if(horseMode){
			if(keyCode==UP){
				horse.horse_goUpStatus();
			}
			if(keyCode==LEFT){
				horse.horse_goLeftStatus();
			}
			if(keyCode==RIGHT){
				horse.horse_goRightStatus();
			}	
		}
	}
	
	//MODE SWITCHING FUNCTION
	public void startMode(){
		startMode=true;
		kiteMode=false;
		horseMode=false;
		jumpMode=false;
		sawMode=false;
		//wiiManager.gameMode=false;
	}
	public void kiteMode(){
		startMode=false;
		kiteMode=true;
		horseMode=false;
		jumpMode=false;
		sawMode=false;
		//wiiManager.gameMode=true;

	}
	public void horseMode(){
		startMode=false;
		kiteMode=false;
		horseMode=true;
		jumpMode=false;
		sawMode=false;
		//wiiManager.gameMode=true;

	}
	public void jumpMode(){
		startMode=false;
		kiteMode=false;
		horseMode=false;
		jumpMode=true;
		sawMode=false;
		//wiiManager.gameMode=true;

	}
	public void sawMode(){
		startMode=false;
		kiteMode=false;
		horseMode=false;
		jumpMode=false;
		sawMode=true;
		//wiiManager.gameMode=true;

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


