package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PneumaticControl extends RobotFunction {
	
	private Compressor m_compressor;
	private DoubleSolenoid m_solenoid;

	public PneumaticControl(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_compressor = new Compressor();
		m_compressor.setClosedLoopControl(true);
		m_solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2);
	}

	@Override
	public void start() {
		m_compressor.setClosedLoopControl(true);
		
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
		
	}

	@Override
	protected void outputHandler() {

	}

}
