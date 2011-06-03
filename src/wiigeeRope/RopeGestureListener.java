package wiigeeRope;
import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;


public class RopeGestureListener implements GestureListener {
	public int gestureID;

	public RopeGestureListener() {
	}

	@Override
	public void gestureReceived(GestureEvent event) {
		gestureID=event.getId();
		System.out.println(gestureID);
		
		//event.getSource().saveGesture(event.getId(), "jump");
		
		if (gestureID==1) { 
			System.out.println("kite");
		}
		else if(gestureID==2) {
			System.out.println("horse");
		}
		else if(gestureID==3){
			System.out.println("jump");
		}
	}

}
