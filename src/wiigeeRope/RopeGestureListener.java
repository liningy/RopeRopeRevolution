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
		//event.getSource().saveGesture(event.getId(), "RopeJumping");
		if (gestureID == 0) { 
			System.out.println("horseDriving");
		}
		else {
			System.out.println("ropejumping");
		}
	}

}
