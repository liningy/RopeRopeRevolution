package kiteFlying;
import processing.core.*;


public class KiteTest {
	PApplet parent;
	
	public PImage wind;
	
	public KiteTest (PApplet p){
		parent=p;
		wind=  parent.loadImage ("wind.png");
	}
	
	public void display(){
		parent.image(wind, 0, 0);
	}

}


