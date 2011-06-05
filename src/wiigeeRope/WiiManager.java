package wiigeeRope;

import java.io.IOException;
import org.wiigee.control.WiimoteWiigee;
import org.wiigee.device.Wiimote;
import org.wiigee.filter.IdleStateFilter;
import org.wiigee.filter.LowPassFilter;
import org.wiigee.filter.RotationThresholdFilter;
import org.wiigee.control.Wiigee;

public class WiiManager {
	private WiimoteWiigee wiigee;
	private Wiimote wiimote;
	public RopeGestureListener ropeGestureListener; 
	public RopeAccelerationListener ropeAccelerationListener;
	//public boolean gameMode=false;

	
	public WiiManager() {
		wiigee = new WiimoteWiigee();
		try {
			wiimote = wiigee.getDevice();
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		wiimote.loadGesture("init");
		wiimote.loadGesture("kite");
		wiimote.loadGesture("horse");
		wiimote.loadGesture("jump");
		wiimote.loadGesture("saw");

		
		wiigee.setTrainButton(Wiimote.BUTTON_A);
		wiigee.setCloseGestureButton(Wiimote.BUTTON_HOME);
		wiigee.setRecognitionButton(Wiimote.BUTTON_B);

		ropeGestureListener=new RopeGestureListener();		
		wiigee.addGestureListener(ropeGestureListener);	


		ropeAccelerationListener=new RopeAccelerationListener();
		wiigee.addDeviceListener(ropeAccelerationListener);
	}

}