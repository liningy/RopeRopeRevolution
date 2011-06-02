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
    
	public boolean menuMode;
	public boolean kiteMode;
	
	
	public void setup(){
		size(1024,768);
		background(0);
		smooth();
		
		kite1= new Kite (this);
		menuSelection = new MenuSelection (this);
		bg=loadImage("chinaBackground.png");
		menuMode=true;
		kiteMode=false;
		
		wiiManager=new WiiManager();
	}
	
	public void draw() {	
		if(menuMode){
			menuSelection.display();
			if(wiiManager.ropeGestureListener.gestureID == 1){
				menuMode=false;
				kiteMode=true;
			}
		}

		if(kiteMode){
			image(bg,0,0);

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
	}
	
	public void keyPressed() {
		//SWITCHING MODE
		if(key== '1'){
			menuMode=false;
			kiteMode=true;
		}
		
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
	
	public static void main(String args[]) {
		PApplet.main(new String[] {"--present", "startPage.Start"});
	}
}


