package kiteFlying;
import processing.core.*;

public class Kite{
	PApplet parent;
	//field
	private PVector kitePosition;
	private int numFramesOfTautKites = 40;  // The number of frames in the animation
	private int frameOfTautKites = 0;
	private PImage[] tautKites = new PImage[numFramesOfTautKites];
	public int tautUp=0;
	public int tautRight=0;
	private double theta;
	private double speed;
	public PImage wind;
	public PImage wind2;
	  
	public boolean pullUp=false;
	public boolean release=false;  
	public boolean pullLeft=false;  
	public boolean pullRight=false; 
	public boolean initialState=false;
	 
	public boolean noWind=true;  
	public boolean leftWind=false;  
	public boolean rightWind=false;
	private boolean flag=false;
	  
	public int score=0;
	public PFont fontForScore;
	
	//arguments
	public Kite(PApplet p){
		parent=p;
		
		for(int i=0; i<numFramesOfTautKites; i++) {
			String imageName = "kiteTaut/"+"RedKite" + parent.nf(i, 4) + ".png";
		    tautKites[i] = parent.loadImage(imageName);
		    tautKites[i].resize(250,250);
		}  
		    
		kitePosition = new PVector(parent.width/2,parent.height-200);
		theta=0;
		speed=1;
		fontForScore = parent.createFont("HelveticaNeue-Light-48.vlw", 32);
		wind=  parent.loadImage ("wind.png");
		wind2= parent.loadImage ("wind2.png");
	}
	
	//functions
	private void display(){
		frameOfTautKites = (frameOfTautKites+1) % numFramesOfTautKites;  // Use % to cycle through frames    
		parent.pushMatrix(); 
		parent.translate(kitePosition.x,  kitePosition.y); 
		parent.imageMode(parent.CENTER);
		parent.scale((kitePosition.y+200)/500);
		parent.image(tautKites[frameOfTautKites],  0,  0);
		parent.imageMode(parent.CORNER);
		parent.popMatrix();
	}
		  
	private void display_leftRotate(double spinSpeed){
		theta -= spinSpeed;
		parent.pushMatrix(); 
		parent.translate(kitePosition.x,  kitePosition.y); 
		parent.rotate((float) theta); 
		frameOfTautKites = (frameOfTautKites+1) % numFramesOfTautKites;  // Use % to cycle through frames
		parent.imageMode(parent.CENTER);
		parent.scale((kitePosition.y+200)/500);
		parent.image(tautKites[frameOfTautKites],  0,  0);
		parent.imageMode(parent.CORNER);
		parent.popMatrix();
	}

	private void display_rightRotate(double spinSpeed){
		theta += spinSpeed;
		parent.pushMatrix(); 
		parent.translate(kitePosition.x,  kitePosition.y); 
		parent.rotate((float) theta); 
		frameOfTautKites = (frameOfTautKites+1) % numFramesOfTautKites;  // Use % to cycle through frames
		parent.imageMode(parent.CENTER);
		parent.scale((kitePosition.y+200)/500);
		parent.image(tautKites[frameOfTautKites],  0,  0);
		parent.imageMode(parent.CORNER);
		parent.popMatrix();
	}

	private void goUp(){
		kitePosition.y-=(speed+3);
		if(kitePosition.y<30) kitePosition.y=30;
	}

	private void fallDown(){
		kitePosition.y+=(speed+2); 
		if(kitePosition.y>parent.height-30) kitePosition.y=parent.height-30;
	}

	private void moveLeft(){
		kitePosition.x-=(speed+2); 
		if(kitePosition.x<30) kitePosition.x=30;
	}

	private void moveRight(){
		kitePosition.x+=(speed+2);
		if(kitePosition.x>parent.width-30) kitePosition.x=parent.width-30;
	}

	public void stayStill(){
		kitePosition.x+=parent.random(-2,2);
		kitePosition.y+=parent.random(-2,2);    
	}

	//flying action in sky
	public void withoutWind(){
		if(initialState){
			display();
		}    
		if(pullUp){
			display();
			goUp();
		}
		if(release){
			display_leftRotate(0.03);
			fallDown();
		}
		if(pullRight){
			display();
			moveRight();
		}
		if(pullLeft){
			display();
			moveLeft();
		}
	}


	public void withWind(){   
		if(pullUp){
			display();
			goUp();
		}
		if(release){
			display_leftRotate(0.03);
			fallDown();
		}
		if(pullRight){
			display();
			moveRight();
		}
		if(pullLeft){
			display();
			moveLeft();
		}
	}

	public void withRightWind(){
		if(initialState){
			display_leftRotate(0.03);
			moveLeft();
			fallDown();
		}

		if(release && !flag){ 
			display();
			//kite1.stayStill();
		}

		if(pullUp || pullRight ){
			flag=true;
			rightWind=false;
			noWind=true;
			leftWind=false;
		}

		if(pullLeft){
			display_leftRotate(0.03);
			moveLeft();
			fallDown();
		}

		parent.image(wind,parent.width-400,100);
	}


	public void withLeftWind(){ 
		if(initialState){
			display_rightRotate(0.03);
			moveRight();
			fallDown();
		}

		if(release && !flag){ 
			display();
			//kite1.stayStill();
		}

		if(pullUp || pullLeft ){
			flag=true;
			rightWind=false;
			noWind=true;
			leftWind=false;
		}

		if(pullRight){
			display_rightRotate(0.03);
			moveRight();
			fallDown();
		} 


		parent.image(wind2,-200,100);
	}

	//INITIAL ACTION DEFINE
	public void initialAction(){
		pullUp = false;
		release=false;
		pullLeft=false;
		pullRight=false;
		initialState=true;
		flag=false;
	}

	//KITE STATUS DEFINE
	public void goUpStatus(){
		pullUp = true;
		release=false;
		pullLeft=false;
		pullRight=false;
		initialState=false;
		theta=0;
	}
	public void goDownStatus(){
		pullUp=false;
		release=true;
		pullLeft=false;
		pullRight=false;    
		initialState=false;
	}
	public void goLeftStatus(){
		pullUp=false;
		release=false;
		pullLeft=true;
		pullRight=false;    
		initialState=false;
		theta=0;
	}
	public void goRightStatus(){
		pullUp=false;
		release=false;
		pullLeft=false;
		pullRight=true;    
		initialState=false;
		theta=0;
	}

	//WIND SIMULATION
	public void noWindStatus(){
		noWind=true;
		initialAction();
		rightWind=false;
		leftWind=false;    
	}
	public void leftWindStatus(){
		rightWind=true;
		initialAction();
		noWind=false;
		leftWind=false;
	}
	public void rightWindStatus(){
		leftWind=true;
		initialAction();
		noWind=false;
		rightWind=false;
	}

	public void writeScoreForKite1(){
		parent.textFont(fontForScore, 48);
		parent.fill(255);
		parent.textAlign(parent.LEFT, parent.TOP);
		//text(kite1Score, 926,94);
		//image(scoreImg,761,101);
	}

	public void writeScoreForKite2(){
		parent.textFont(fontForScore, 48);
		parent.fill(255);
		parent.textAlign(parent.LEFT, parent.TOP);
		//text(kite2Score, 926,147);
		//image(scoreImg,761,154);
	}

}
