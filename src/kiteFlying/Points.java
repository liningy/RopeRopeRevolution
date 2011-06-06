package kiteFlying;
import processing.core.*;
import startPage.Start;


public class Points {
	PApplet parent;
	public PVector pointPosition;
	int frames=4;
	PImage[] points = new PImage[frames];
	public boolean flag=false;
	public boolean randomeSelectionFlag=false;
	float beginTime;
	float currentTime;
	public int imageFrame=0;
	Kite kite;
	int kiteScore;
	
	public Points (PApplet p, int positionX, int positionY){
		parent=p;
		points[0]=parent.loadImage("add5.png");
		points[1]=parent.loadImage("add25.png");    
		points[2]=parent.loadImage("minus10.png");
		points[3]=parent.loadImage("minus50.png");   
		pointPosition = new PVector(positionX,positionY);
	}
	
	  //FUNTIONS
	public void randomSelection(){
		if(randomeSelectionFlag){
			pointPosition.x=(int)(parent.random(100,parent.width-200));
			pointPosition.y=(int)(parent.random(0,parent.height-300));
			imageFrame=(int)(parent.random(0,4));
			randomeSelectionFlag=false; 
			//sendCloudMessage((int)pointPosition.x, (int)pointPosition.y, imageFrame);
		}
	}     

	public void display(){
		if(flag){
			parent.imageMode(parent.CENTER);
			parent.image(points[imageFrame], pointPosition.x,pointPosition.y); 
			parent.imageMode(parent.CORNER);
		}
	} 

	public void displayTime(){
		if(!flag){
			beginTime=parent.millis();
			flag=true;      
		}
		if(parent.millis()-beginTime>10000){
			randomeSelectionFlag=true;
			flag=false;
		}
	}

	public void randomCloudDisplay(){
		displayTime();
		display();
		randomSelection();
	}


}
