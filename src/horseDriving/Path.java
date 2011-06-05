package horseDriving;
import processing.core.PApplet;
import processing.core.PVector;
import java.awt.Graphics;



public class Path {
	//FIELD
    float baseX1, baseY1, baseX2, baseY2;
    float baseLength;
	float[] xCoords, yCoords;
	float boundaryDist;
	
	//CONSTRUCTOR
	public Path (float _baseX1, float _baseY1, float _baseX2, float _baseY2){
		baseX1=_baseX1;
		baseY1=_baseY1;
		baseX2=_baseX2;
		baseY2=_baseY2;
		baseLength = PApplet.dist(baseX1, baseY1, baseX2, baseY2);
		xCoords = new float[PApplet.ceil(baseLength)];
		yCoords = new float[PApplet.ceil(baseLength)];

		// fill base top coordinate array
		for (int i=0; i<xCoords.length; i++){
			xCoords[i] = baseX1 + ((baseX2-baseX1)/baseLength)*i;
			yCoords[i] = baseY1 + ((baseY2-baseY1)/baseLength)*i;
		}
		
		boundaryDist=4;
	}
	
	//FUNCTION
//	void detectCollision(PVector horsePosition){
//		for (int i=0; i<xCoords.length; i++){
//			// check distance between ellipse and base top coordinates
//			if (PApplet.dist(horsePosition.x, horsePosition.y, xCoords[i], yCoords[i]) < boundaryDist){
//
//				horsePosition.x=xCoords[i];
//				horsePosition.y=yCoords[i];
//			}
//		}
//	}
}
