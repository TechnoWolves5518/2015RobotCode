package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickOI {

	private Joystick stick;
	private Joystick xbox;
	
	public JoystickOI() {
		stick = new Joystick(RobotMap.JOYSTICK_PORT1);
		xbox = new Joystick(RobotMap.JOYSTICK_PORT2);
	}
	
}
