package jumpRoping;
import processing.core.*;


public class Jump {
	PApplet parent;	
	
	//FIELD
	private PVector jumpPosition;
	private int numFramesOfJump = 9;  // The number of frames in the animation
	public int frameOfJump = 0;
	private PImage[] jumps = new PImage[numFramesOfJump];
	private int loop_jump=0;
	private int numofLoop_jump = 6;
	
	//ARGUMENT
	public Jump (PApplet p){
		parent=p;
		for(int i=0; i < numFramesOfJump; i++) {
			String imageName = "jump/"+"jump" + parent.nf(i, 4) + ".png";
			jumps[i] = parent.loadImage(imageName);			
		}     
		jumpPosition = new PVector(parent.width/2,parent.height/2);
	}
	
	//FUNCTION
//	public void display_jump(){
//		
//		parent.pushMatrix(); 
//		parent.translate(jumpPosition.x,  jumpPosition.y);
//		
//		loop_jump = (loop_jump + 1 ) % numofLoop_jump;
//				
//		if(loop_jump==(numofLoop_jump-1)) {
//			frameOfJump = (frameOfJump+1) % numFramesOfJump;  // Use % to cycle through frames    
//		}
//		
//		parent.imageMode(parent.CENTER);
//		if(frameOfJump == 0)
//			frameOfJump = 1;
//		parent.image(jumps[frameOfJump],  0,  0);
//		parent.imageMode(parent.CORNER);
//		parent.popMatrix();
//	}	
	
	public void runALoop(){
		parent.pushMatrix(); 
		parent.translate(jumpPosition.x,  jumpPosition.y);
		parent.imageMode(parent.CENTER);
		
		//for(int i=0; i<numFramesOfJump-2; i++){


		if(frameOfJump<8) {
			if(frameOfJump == 0)
				frameOfJump = 1;
			parent.image(jumps[frameOfJump],  0,  0);
			loop_jump = (loop_jump + 1 ) % numofLoop_jump;
			if(loop_jump==(numofLoop_jump-1)) {
				frameOfJump = (frameOfJump+1)%numFramesOfJump;  // Use % to cycle through frames    
			}
		} 
		else{
			parent.image(jumps[0],  0,  0);
		}
		parent.imageMode(parent.CORNER);
		parent.popMatrix();
		System.out.println(frameOfJump);
	}
}
