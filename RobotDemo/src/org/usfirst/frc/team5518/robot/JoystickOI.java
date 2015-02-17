package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickOI {

	private Joystick stick;
	
	public JoystickOI() {
		
		// instantiate joystick variable
		stick = new Joystick(RobotMap.JOYSTICK_PORT1);
		
		// instantiate joystick buttons here & connect commands to them
		mapControls();
	}
	
	private void mapControls() {
	}
	
	public void setJoystick(int port) {
		stick = new Joystick(port);
	}
	
	public Joystick getJoystick() {
		return stick;
	}
	
}
