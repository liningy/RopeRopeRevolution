package menuSelection;
import processing.core.*;


public class MenuSelection {
	PApplet parent;
	public PImage menuSelection;
	
	
	public MenuSelection (PApplet p){
		parent=p;
		menuSelection=  parent.loadImage ("menuSelection-01.jpg");
	}

	public void display(){
		parent.image(menuSelection, 0, 0);
	}

}
