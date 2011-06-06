package wiigeeRope;
import org.wiigee.event.AccelerationListener;
import org.wiigee.event.AccelerationEvent;
import org.wiigee.event.MotionStartEvent;
import org.wiigee.event.MotionStopEvent;

public class RopeAccelerationListener implements AccelerationListener {
 
	private double currX,  currY,  currZ;
	private double lastX,  lastY,  lastZ;
	public  double diffX,  diffY,  diffZ; 
	public  double lastDiffZ, lastDiffY;
	public  double diffDiffZ, diffDiffY;
	private int multi;
    public boolean goingUp = false;
    public boolean goingDown = false;
    
    private boolean inMotion = false;
    
    public int upAndDown=0;
    public int horseMove=0;
    long start=0;


    
	public RopeAccelerationListener() {
		multi=10;
	}
	

    public void accelerationReceived (AccelerationEvent e) {
    	if (inMotion = false){
    		return;
    	}
    	
        lastDiffZ=diffZ;
        lastDiffY=diffY;
    	lastX = currX;
        lastY = currY;
        lastZ = currZ;
        currX = (e.getX()*multi); 
        currY =  (e.getY()*multi);
        currZ =  (e.getZ()*multi);
    	diffX=currX-lastX;
    	diffY=currY-lastY;
    	diffZ=currZ-lastZ;
    	diffDiffZ=diffZ-lastDiffZ;
    	diffDiffY=diffY-lastDiffY;
    	
    	
        //HORSE MOVING
    	if(diffZ>4 || diffZ<-4){
    		if(java.lang.Math.abs(diffDiffZ)>8){
    			//System.out.println("horseMove");
    			horseMove=1;
        		start=System.currentTimeMillis();
    		}
    	}
    	if(horseMove>0){
    		long elapsedTimeMillis = System.currentTimeMillis() - start;
    		if(elapsedTimeMillis>1000) horseMove=0;
    	}
    	
    	//ROPE DETECTION
    	if(e.getAbsValue()>5){
    		if((System.currentTimeMillis() - start)>40){
    			upAndDown=1;
    			start=System.currentTimeMillis();
    		}
    		else{
    			upAndDown=0;
    		}
    	}
    	else{
    		upAndDown=0;
    	}
    	
    	System.out.println("horseMove="+horseMove+" upAndDown="+upAndDown);



    }
    
	@Override
	public void motionStartReceived(MotionStartEvent event) {
		inMotion = true;
	}

	@Override
	public void motionStopReceived(MotionStopEvent event) {
		inMotion = false;
		goingUp = false;
		goingDown = false;
		upAndDown=0;
		horseMove=0;
    	System.out.println("horseMove="+horseMove+" upAndDown="+upAndDown);
		
	}
}


