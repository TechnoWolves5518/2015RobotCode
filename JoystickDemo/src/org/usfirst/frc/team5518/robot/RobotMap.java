package org.usfirst.frc.team5518.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int FRONT_LEFT_MOTOR = 2;
	public static final int REAR_LEFT_MOTOR = 1; 
	public static final int FRONT_RIGHT_MOTOR = 3; 
	public static final int REAR_RIGHT_MOTOR = 0;
	
	public static final int JOYSTICK_PORT = 0;
	public static final int SENSITIVITY_BUFFER = 1;
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int Z_AXIS = 2;   // joystick rotate axis
	public static final int TRIGGER_AXIS = 4;  // xbox trigger axis
	public static final int BTN_TRIGGER = 0;   // joystick trigger button & xbox A button
	
	public static final int SOLENOID_PCM_PORT1 = 0;
	public static final int SOLENOID_PCM_PORT2 = 1;

}
