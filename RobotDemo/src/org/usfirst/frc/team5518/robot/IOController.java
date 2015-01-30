package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IOController {

	private Joystick stick;
	private Joystick xbox;
	
	public IOController() {
		stick = new Joystick(RobotMap.JOYSTICK_PORT1);
		xbox = new Joystick(RobotMap.JOYSTICK_PORT2);
	}
	
}
