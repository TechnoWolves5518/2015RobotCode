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

	public static final int FRONT_LEFT_MOTOR = 0;
	public static final int REAR_LEFT_MOTOR = 1; 
	public static final int FRONT_RIGHT_MOTOR = 2;
	public static final int REAR_RIGHT_MOTOR = 3;
	
	public static final int LIFT_MOTOR = 4;
	
	public static final int JOYSTICK_PORT1 = 0;  // joystick port on DS
	public static final int JOYSTICK_PORT2 = 1;  // joystick port on DS
	//public static final double SENSITIVITY_BUFFER = 1.25d;
	
	public static final int X_AXIS = 0;  // joystick vertical axis
	public static final int Y_AXIS = 1;  // joystick horizontal axis
	public static final int Z_AXIS = 2;   // joystick rotate axis
	public static final int SLIDER_AXIS = 3;  // joystick slider axis
	public static final int BTN_TRIGGER = 1;  // joystick trigger button
	public static final int BTN_2 = 2;  // joystick 2 button
	
	public static final int XBOX_LX_AXIS = 0;  // xbox left thumbstick vertical axis
	public static final int XBOX_LY_AXIS = 1;  // xbox right thumbstick horizontal axis
	public static final int XBOX_LT_AXIS = 2;   // xbox left trigger axis
	public static final int XBOX_RT_AXIS = 3;  // xbox right trigger axis
	public static final int XBOX_RX_AXIS = 4;  // xbox right thumbstick vertical axis
	public static final int XBOX_RY_AXIS = 5;  // xbox right thumbstick horizontal axis
	public static final int XBOX_BTN_A = 1;   // xbox A button
	public static final int XBOX_BTN_B = 2;   // xbox B button
	public static final int XBOX_BTN_X = 3;   // xbox X button
	public static final int XBOX_BTN_Y = 4;   // xbox Y button
	public static final int XBOX_BTN_LB = 5;   // xbox LB button
	public static final int XBOX_BTN_RB = 6;   // xbox RB button
	public static final int XBOX_BTN_BACK = 7;   // xbox back button
	public static final int XBOX_BTN_START = 8;   // xbox start button
	public static final int XBOX_BTN_LS = 9;  // xbox left stick button
	public static final int XBOX_BTN_RS = 10;  // xbox right stick button
	
	public static final int SOLENOID_PCM_PORT1 = 0;  // solenoid pcm port
	public static final int SOLENOID_PCM_PORT2 = 1;  // solenoid pcm port
	
	public static final int ENCODER_DIO_PORTA = 0;  // encoder DIO port on RoboRIO
	public static final int ENCODER_DIO_PORTB = 1;  // encoder DIO port on RoboRIO
	
	/*public static final int SENSOR_DIO_PORT1 = 2; // ultrasonic DIO port
	public static final int SENSOR_DIO_PORT2 = 9; // ultrasonic DIO port*/
	public static final int SENSOR_ANALOG_PORT = 0; // ultrasonic analog port
	
	public static final int LIMIT_DIO_PORT1 = 2;  // limit switch DIO port
	//public static final int LIMIT_DIO_PORT2 = 3;  // limit switch DIO port
	
}
