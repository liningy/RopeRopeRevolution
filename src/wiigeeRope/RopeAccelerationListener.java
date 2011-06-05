package wiigeeRope;
import org.wiigee.event.AccelerationListener;
import org.wiigee.event.AccelerationEvent;
import org.wiigee.event.MotionStartEvent;
import org.wiigee.event.MotionStopEvent;

public class RopeAccelerationListener implements AccelerationListener {
 
    private double currX,  currY,  currZ;
    private double lastX,  lastY,  lastZ;
    public  double diffX,  diffY,  diffZ; 
    public  double lastDiffZ;
    public  double diffDiffZ;
    private int multi;
    
    public boolean goingUp = false;
    public boolean goingDown = false;
    
    private boolean inMotion = false;
    
    public int horseMove=0;
    long start;


    
	public RopeAccelerationListener() {
		multi=10;
	}
	

    public void accelerationReceived (AccelerationEvent e) {
    	if (inMotion = false){
    		return;
    	}
        lastDiffZ=diffZ;
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
    	//diffZ=java.lang.Math.abs(currZ-lastZ);
    	//System.out.println(diffX);
    	//System.out.println(e.getAbsValue());
   	    //System.out.println("diffX="+ diffX);
    	//System.out.println("diffY="+ diffY);
    	
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
    	
    	System.out.println(horseMove);
    	
//    	if(diffZ>0.1 || diffZ<-0.1){
//    	System.out.println(diffZ);
//        System.out.println("");
//    	}
//    	if (diffZ > 0) {
//    		goingUp = false;
//    		goingDown = true;
//    	}
//    	else {
//    		goingUp = true;
//    		goingDown = false;
//    	}


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
		horseMove=0;
    	System.out.println(horseMove);
		
	}
}


