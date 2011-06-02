package wiigeeRope;

import java.io.IOException;
import org.wiigee.control.WiimoteWiigee;
import org.wiigee.device.Wiimote;
import org.wiigee.control.Wiigee;

public class WiiManager {
	private WiimoteWiigee wiigee;
	private Wiimote wiimote;
	public RopeGestureListener ropeGestureListener; 

	
	public WiiManager() {
		wiigee = new WiimoteWiigee();
		try {
			wiimote = wiigee.getDevice();
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		wiimote.loadGesture("horseDriving");
		wiimote.loadGesture("Ropejumping");
		
		wiigee.setTrainButton(Wiimote.BUTTON_A);
		wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
		wiigee.setRecognitionButton(Wiimote.BUTTON_B);
		
		ropeGestureListener=new RopeGestureListener();		
		wiigee.addGestureListener(ropeGestureListener);		
	}

}