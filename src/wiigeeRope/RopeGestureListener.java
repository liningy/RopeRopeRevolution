package wiigeeRope;
import java.io.IOException;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;


public class RopeGestureListener implements GestureListener {
	public int gestureID;

	public RopeGestureListener() {
		gestureID=0;
	}

	@Override
	public void gestureReceived(GestureEvent event) {
		if(event!=null){
		gestureID=event.getId();
		System.out.println(gestureID);
		}
		
		//event.getSource().saveGesture(event.getId(), "saw");
	}

}
