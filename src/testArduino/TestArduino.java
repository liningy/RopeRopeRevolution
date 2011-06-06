//package testArduino;
// 
//import processing.core.PApplet;
//import testArduino.Arduino;
//import gnu.io.*;
// 
//public class TestArduino extends PApplet {
// 
//	public static void main(String args[]) {
//									   //package.javafilename
//		PApplet.main(new String[] { "testArduino.TestArduino" });
//		// if you put the following, it will put it in full screen:
//		// PApplet.main(new String[] { "--present", "arduinotest.ArduinoTest" });
//	}
// 
//	Arduino arduino;	//create an arduino object
//	int pin13 = 13;		//create a variable for a pin on the arduino
//	int sensorValue;
// 
//	public void setup()	//setup the arduino and anything else for your program
//	{
//		//to set the baud rate rather than go off the default (default is = 115200):
//		arduino = new Arduino(this, Arduino.list()[0], 57600);
//		//otherwise,this is how to set up using default:
//		//arduino = new Arduino(this, Arduino.list()[0]);
// 
//		//setup if the pins are input or output
//		arduino.pinMode(pin13, Arduino.OUTPUT);
//	}
// 
//	public void draw()	//this "draw" function; just loops until you shut off the program
//	{
//		arduino.digitalWrite(pin13, Arduino.HIGH);
//		//delay(1000);
//		arduino.digitalWrite(pin13, Arduino.LOW);
//		//delay(1000);
//		
//		sensorValue = arduino.analogRead(1);
//		System.out.println(sensorValue);
//	}
//}						//end of TestArduino.java class