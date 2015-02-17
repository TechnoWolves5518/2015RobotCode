package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XControllerOI {

private Joystick xbox;

	public XControllerOI() {
		
		// instantiate xbox variable
		xbox = new Joystick(RobotMap.JOYSTICK_PORT2);
		
		// instantiate xbox buttons here & connect commands to them
		mapControls();
	}
	
	private void mapControls() {
	}
	
	public void setJoystick(int port) {
		xbox = new Joystick(port);
	}
	
	public Joystick getJoystick() {
		return xbox;
	}
	
	/*public JoystickButton getJoystickBtn(int button) {
		switch (button) {
		case RobotMap.XBOX_BTN_A:
			return xbox_a;
		case RobotMap.XBOX_BTN_RS:
			return xbox_rs;
		default:
			return null;
		}
	}*/
	
}
