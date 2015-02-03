package org.usfirst.frc.team5518.robot;

/**
 * RobotMap class provides constants for various port
 * numbers on the Robot's PDP, PCM and Computer's DS
 * 
 * PDP - Power Distribution Panel
 * PCM - Pneumatics Control Module
 * DS - Driver Station
 * 
 **/
public class RobotMap {

	public static final int FRONT_LEFT_MOTOR = 2;
	public static final int REAR_LEFT_MOTOR = 1; 
	public static final int FRONT_RIGHT_MOTOR = 3; 
	public static final int REAR_RIGHT_MOTOR = 0;
	
	public static final int JOYSTICK_PORT1 = 0;  // joystick port on DS
	public static final int JOYSTICK_PORT2 = 1;  // joystick port on DS
	public static final double SENSITIVITY_BUFFER = 1.25d;
	
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int Z_AXIS = 2;   // joystick rotate axis
	public static final int BTN_TRIGGER = 0;   // joystick trigger button
	
	public static final int XBOX_X_AXIS = 0;
	public static final int XBOX_Y_AXIS = 1;
	public static final int XBOX_LT_AXIS = 2;   // xbox left trigger axis
	public static final int XBOX_TRIGGER_AXIS = 4;  // xbox trigger axis
	public static final int XBOX_BTN_TRIGGER = 0;   // xbox A button
	
	public static final int SOLENOID_PCM_PORT1 = 0;
	public static final int SOLENOID_PCM_PORT2 = 1;
	
}
