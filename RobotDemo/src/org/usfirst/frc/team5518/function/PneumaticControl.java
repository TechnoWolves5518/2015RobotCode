package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticControl extends RobotFunction {
	
	private Compressor m_compressor;
	private DoubleSolenoid m_solenoid;
	
	private Joystick prevStick;
	
	private int comp_state = 0;  // compressor power state at next loop

	public PneumaticControl(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_compressor = new Compressor();
		m_compressor.setClosedLoopControl(true);
		m_compressor.startLiveWindowMode();
		m_solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2);
		//m_solenoid.startLiveWindowMode();
	}

	@Override
	public void start() {
		
		if (Robot.xOi.getJoystick().getRawAxis(RobotMap.XBOX_LT_AXIS) > 0.5d) {  // if left trigger pressed more than halfway
			// open solenoid valves to make pistons go forward
    		m_solenoid.set(DoubleSolenoid.Value.kForward);  
    	} else if (Robot.xOi.getJoystick().getRawAxis(RobotMap.XBOX_LT_AXIS) <= 0.5d) {  // if left trigger pressed less or equal to halfway
    		// open solenoid values to make pistons go backward
    		m_solenoid.set(DoubleSolenoid.Value.kReverse);  
    	} else {  // otherwise
    		// close both solenoid valves and prevent air from flowing
    		m_solenoid.set(DoubleSolenoid.Value.kOff); 
    	}
		
		 if (Robot.xOi.getJoystickBtn(RobotMap.XBOX_BTN_RS).get()) {
			 switch (comp_state) {
			 case 0:  // compressor off
				 m_compressor.setClosedLoopControl(false);
				 comp_state = 1;
				 break;
			 case 1:  // compressor on
				 m_compressor.setClosedLoopControl(true);
				 comp_state = 0;
			 }
		 }
		 
		 prevStick = Robot.xOi.getJoystick();
		
	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	protected void log() {
		super.log();
		SmartDashboard.putNumber("Compressor Current", m_compressor.getCompressorCurrent());
	}
	
}
