package menuSelection;
import processing.core.*;
import startPage.Start;


public class MenuSelection {
	PApplet parent;
	public PImage menuSelection;
	
	
	public MenuSelection (PApplet p){
		parent=p;
		menuSelection=  parent.loadImage ("menuSelection.png");
	}

	public void display(){
		parent.image(menuSelection, 0, 0);
	}

}
