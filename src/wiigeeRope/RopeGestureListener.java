package wiigeeRope;
import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;


public class RopeGestureListener implements GestureListener {

	public RopeGestureListener() {
	}

	@Override
	public void gestureReceived(GestureEvent event) {
		System.out.println(event.getId());
		//event.getSource().saveGesture(event.getId(), "RopeJumping");
		if (event.getId() == 0) { 
			System.out.println("horseDriving");
		}
		else {
			System.out.println("ropejumping");
		}
	}

}
