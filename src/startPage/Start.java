package startPage;

import jumpRoping.Jump;
import horseDriving.Horse;
import processing.core.*;
import kiteFlying.Kite;
import kiteFlying.Points;
import menuSelection.MenuSelection;
import testArduino.Arduino;
import wiigeeRope.WiiManager;
import processing.serial.*;
import ddf.minim.*;
import kiteFlying.Points;



public class Start extends PApplet  {
	private static final long serialVersionUID = 1L;
	
	public MenuSelection menuSelection;
	public Kite kite;
	public Horse horse;
	public Jump jump;
	public PImage bg;
    
	public boolean startMode,kiteMode,horseMode,jumpMode,sawMode;
	public int startPointX, startPointY;
	
	public boolean horseForward=false;
	public long horseStart;
	
	//SERIAL
	int oldSensorValue, sensorValue;
	int oldSensor1Value, sensor1Value;
	private Serial arduinoSerial;
	Arduino arduino;
	
    
	//SOUND
	AudioPlayer song1,song2,song3,song3_2;
	Minim minim;
	boolean isPlaying_kite=false;
	boolean isPlaying_horse=false;
	boolean isPlaying_jump=false;
	
	//SCORE
	int totalScore;
	Points points;
	PImage scoreImg;
	public PFont fontForScore;



	
	//WIIGEE
	public WiiManager wiiManager;
	
	
	public void setup(){
		size(1024,768);
		background(0);
		smooth();
		
		bg=loadImage("bg.png");
		startPointX=0;
		startPointY=0;
		
		
		menuSelection = new MenuSelection (this);
		
		startMode();
		
		//SERIAL
		arduino = new Arduino(this, Arduino.list()[0], 57600);
	    sensorValue = 960;
	    oldSensorValue = 960;

		
		//SOUND
		minim=new Minim(this);
		song1=minim.loadFile("bgmusic/china.wav"); 
		song2=minim.loadFile("bgmusic/horse.wav");  
		song3=minim.loadFile("bgmusic/jumpRoping.wav");  
		song3_2=minim.loadFile("bgmusic/jump.wav");  
		
		//SCORE
		points=new Points(this,200,200);
		scoreImg=loadImage("scoreImg.png");
		totalScore=0;
		fontForScore = createFont("HelveticaNeue-Light-48.vlw", 32);


		//WIIGEE
		//wiiManager=new WiiManager();

	}
	
	public void draw() {
		image(bg,startPointX,startPointY);
		
		if(startMode){
			bgMoving(0,0);
			menuSelection.display();
			
			//WIIGII_Gesture Menu Selection
//			if(wiiManager.ropeGestureListener.gestureID == 1){
//				kiteMode();
//				kite= new Kite (this);
//				wiiManager.ropeGestureListener.gestureID =0;
//			} 
//			else if (wiiManager.ropeGestureListener.gestureID == 2){
//				horseMode();
//				horse=new Horse(this);
//				wiiManager.ropeGestureListener.gestureID =0;
//			}
//			else if(wiiManager.ropeGestureListener.gestureID == 3){
//				jumpMode();
//			    jump=new Jump(this);
//				wiiManager.ropeGestureListener.gestureID =0;
//			}
//			else if(wiiManager.ropeGestureListener.gestureID == 4){
//				sawMode();
//				wiiManager.ropeGestureListener.gestureID =0;
//			}
		}

		if(kiteMode){
			arduinoDetect();
			
			song1.play();
			isPlaying_kite=true;
			
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
			
			points.randomCloudDisplay();  
			hitKite();
			}
		
		if(horseMode){
			song2.play();
			isPlaying_horse=true;
			
			bgMoving(0,-768);
			horse.horseDriving();	
			
			//WIIGEE_GESTURE RECOGNITION
//			if(wiiManager.ropeAccelerationListener.horseMove==1){
//				horseForward=true;
//				horseStart=System.currentTimeMillis();
//			}
//			else horseForward=false;
//			
//			if(horseForward){
//				horse.horse_goUpStatus();
//				horseForward=false;
//			}
//			else{
//				horse.horse_stayStill();
//			}
		}
		
		if(jumpMode){
			song3.play();
			isPlaying_jump=true;
			
			bgMoving(-1024,-768);
			jump.runALoop();
			//WIIGEE_GESTURE RECOGNITION
//			if(wiiManager.ropeAccelerationListener.upAndDown==1){
//				jump.frameOfJump=0;
//			    song3_2.play();
//			}
		}
		
		if(sawMode){
			bgMoving(-2040,-768);
		}
		
		writeScores();
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
			horse= new Horse (this);
		}
		if(key=='3') {
			jumpMode();
			jump=new Jump(this);
		}
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
		
		//JUMP ROPING
		if(jumpMode){
			if(keyCode==UP){
				jump.frameOfJump=0;
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
		if(isPlaying_kite) song1.pause();
		if(isPlaying_horse) song2.pause();
		if(isPlaying_jump) song3.pause();
		//wiiManager.gameMode=false;
	}
	public void kiteMode(){
		startMode=false;
		kiteMode=true;
		horseMode=false;
		jumpMode=false;
		sawMode=false;
		if(isPlaying_horse) song2.pause();
		if(isPlaying_jump) song3.pause();
		//wiiManager.gameMode=true;

	}
	public void horseMode(){
		startMode=false;
		kiteMode=false;
		horseMode=true;
		jumpMode=false;
		sawMode=false;
		if(isPlaying_kite) song1.pause();
		if(isPlaying_jump) song3.pause();
		//wiiManager.gameMode=true;

	}
	public void jumpMode(){
		startMode=false;
		kiteMode=false;
		horseMode=false;
		jumpMode=true;
		sawMode=false;
		if(isPlaying_kite) song1.pause();
		if(isPlaying_horse) song2.pause();
		//wiiManager.gameMode=true;

	}
	public void sawMode(){
		startMode=false;
		kiteMode=false;
		horseMode=false;
		jumpMode=false;
		sawMode=true;
		if(isPlaying_kite) song1.pause();
		if(isPlaying_horse) song2.pause();
		if(isPlaying_jump) song3.pause();

		//wiiManager.gameMode=true;

	}
	public void bgMoving(int xPosition, int yPosition){
	    if(startPointX>xPosition) startPointX-=8;
	    if(startPointX<xPosition) startPointX+=8;
	    if(startPointY>yPosition) startPointY-=8;
	    if(startPointY<yPosition) startPointY+=8;
	}
	
	//KITE SERIAL READING FUNCTION
	void arduinoDetect(){ 
		oldSensor1Value = sensor1Value;
		sensor1Value = arduino.analogRead(3);

		if ((sensor1Value - oldSensor1Value) > 10) { 
			kite.goLeftStatus();
		}
		if ((sensor1Value - oldSensor1Value) < -10) {
			kite.goRightStatus();
		}


		oldSensorValue = sensorValue;
		sensorValue = arduino.analogRead(0);
		if ((sensorValue - oldSensorValue) > 5) {
			kite.goDownStatus();
		}
		if ((sensorValue - oldSensorValue) < -5) {
			kite.goUpStatus();
		}
		System.out.println(sensorValue);
	}
	
	//SCORE ACCUMLATING
	public void hitKite(){
		if (dist(kite.kitePosition.x, kite.kitePosition.y, points.pointPosition.x, points.pointPosition.y)<50) {
			int gainedScore=0;
			if(points.imageFrame==0) gainedScore=5;
			if(points.imageFrame==1) gainedScore=25;        
			if(points.imageFrame==2) gainedScore=-10;        
			if(points.imageFrame==3) gainedScore=-50;
			totalScore+=gainedScore;
			points.flag=false;
			points.randomeSelectionFlag=true;
		}
	}
	
	public void writeScores(){
		textFont(fontForScore, 48);
		fill(255);
		textAlign(LEFT, TOP);
		text(totalScore, 926,94);
		image(scoreImg,761,101);
	}
	
	
	public static void main(String args[]) {
		PApplet.main(new String[] {"--present", "startPage.Start"});
	}
}


