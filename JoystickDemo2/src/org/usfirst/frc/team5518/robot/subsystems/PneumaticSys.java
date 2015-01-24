package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticSys extends Subsystem {
	
	private DoubleSolenoid solenoid;
    private Compressor compressor;
    
    public PneumaticSys() {
    	super();
    	solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2); // PCM port #0 & #1
    	compressor = new Compressor(); // Compressor is controlled automatically by PCM
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void solenoidValve(int state) {
    	switch (state) {
    	case 0:
    		solenoid.set(DoubleSolenoid.Value.kOff);
    		break;
    	case 1:
    		solenoid.set(DoubleSolenoid.Value.kForward);
    		break;
    	case 2:
    		solenoid.set(DoubleSolenoid.Value.kReverse);
    		break;
    	}
    }
    
    public void compressorOn(boolean state) {
    	if (state) compressor.setClosedLoopControl(true);
    	else compressor.setClosedLoopControl(false);
    }
}

