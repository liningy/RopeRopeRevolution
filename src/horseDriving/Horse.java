package horseDriving;
import processing.core.*;
import startPage.Start;


public class Horse {
	PApplet parent;	
	//FIELD
	private PVector horsePosition;
	private int numFramesOfHorse = 3;  // The number of frames in the animation
	private int frameOfHorses = 0;
	private PImage[] horses = new PImage[numFramesOfHorse];
	private int loop=0;
	private int numofLoop=7;
	private double speed;
	public boolean horse_goUp = false;
	public boolean horse_pullLeft=false;
	public boolean horse_pullRight=false;
	public boolean horse_stayStill=true;
	//private Path path1;
	private Path[] paths=new Path[6];
	public boolean collisionHappen=false;
	public boolean isLeftCollision=true;
    public float collisionX;
	
	
	public Horse (PApplet p){
		parent=p;
		for(int i=0; i<numFramesOfHorse; i++) {
			String imageName = "horse/"+"horse" + parent.nf(i, 4) + ".png";
			horses[i] = parent.loadImage(imageName);
			horses[i].resize(160,294);
		}  
		    
		horsePosition = new PVector(parent.width/2,parent.height-50);
		speed=1;
		//path1=new Path (0, 488, 370,365);
		
		paths[0]=new Path(0, 488, 370,365);
		paths[1]=new Path(370,365, 307,163);
		paths[2]=new Path(307,163, 530,0);
		paths[3]=new Path(882, 768, 877,380);
		paths[4]=new Path(877,380, 508,163);
		paths[5]=new Path(508,163, 603,0);
		
	}
	
	//FUNCTION
	public void display(){
		parent.pushMatrix(); 
		parent.translate(horsePosition.x,  horsePosition.y); 
		loop=(loop+1)%numofLoop;
		if(loop==(numofLoop-1)) frameOfHorses = (frameOfHorses+1) % numFramesOfHorse;  // Use % to cycle through frames    
		parent.imageMode(parent.CENTER);
		parent.scale((horsePosition.y+200)/500);
		parent.image(horses[frameOfHorses],  0,  0);
		parent.imageMode(parent.CORNER);
		//System.out.println(horses[frameOfHorses] + " frameOfhorse: " + frameOfHorses + "loop=" + loop);
		parent.popMatrix();
	}
	public void horse_goUp(){
		horsePosition.y-=(speed+0.2);
		if(horsePosition.y<30) horsePosition.y=2;
	}

	public void horse_moveLeft(){
		horsePosition.x-=(speed+2); 
		if(horsePosition.x<30) horsePosition.x=30;
	}

	public void horse_moveRight(){
		horsePosition.x+=(speed+2);
		if(horsePosition.x>parent.width-30) horsePosition.x=parent.width-30;
	}


	//flying action in sky
	public void horseDriving(){	
		if(horse_stayStill){
			display();
		}    
		if(horse_goUp){
			display();
			horse_goUp();
		}
		if(horse_pullRight){
			display();
			horse_moveRight();
		}
		if(horse_pullLeft){
			display();
			horse_moveLeft();
		}
		
		//COLLISION
		if(collisionHappen){
			if(isLeftCollision){
				if(horsePosition.x<=collisionX) horse_stayStill(); 
				else collisionHappen=false;
			} 
			else
			{
				if(horsePosition.x>=collisionX) horse_stayStill(); 
				else collisionHappen=false;	
			}
		}
		//collision();
		mutipleCollision();
	}
	
	//STATUS
	public void horse_goUpStatus(){
		horse_goUp = true;
		horse_stayStill=false;
		horse_pullRight=false;
		horse_pullLeft=false;
	}
	public void horse_goLeftStatus(){
		horse_goUp = false;
		horse_stayStill=false;
		horse_pullRight=false;
		horse_pullLeft=true;
	}
	public void horse_goRightStatus(){
		horse_goUp = false;
		horse_stayStill=false;
		horse_pullRight=true;
		horse_pullLeft=false;
	}
	public void horse_stayStill(){
		horse_goUp = false;
		horse_stayStill=true;
		horse_pullRight=false;
		horse_pullLeft=false;
	}
	
	//DETECT COLLISION
//	public void collision(){
//		parent.line(path1.baseX1, path1.baseY1, path1.baseX2, path1.baseY2);		
//		for (int i=0; i<path1.xCoords.length; i++){
//			// check distance between ellipse and base top coordinates
//			if (parent.dist(horsePosition.x, horsePosition.y, path1.xCoords[i], path1.yCoords[i]) < path1.boundaryDist){
//				horse_stayStill();
//				collisionHappen=true;
//				collisionX=path1.xCoords[i];
//			}
//		}
//	}
	
	public void mutipleCollision(){
		for (int j=0;j<6;j++){
			//parent.line(paths[j].baseX1, paths[j].baseY1, paths[j].baseX2, paths[j].baseY2);		
			for (int i=0; i<paths[j].xCoords.length; i++){
				// check distance between ellipse and base top coordinates
				if (parent.dist(horsePosition.x, horsePosition.y, paths[j].xCoords[i], paths[j].yCoords[i]) < paths[j].boundaryDist){
					horse_stayStill();
					collisionHappen=true;
					collisionX=paths[j].xCoords[i];
					if(j<3) isLeftCollision=true; //to detect is left or right collision
					else isLeftCollision=false;
				}
			}		
		}
	}

}
