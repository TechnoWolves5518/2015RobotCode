package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.ArcadeDriveJoystick;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivingSys extends Subsystem {
    
	private RobotDrive robotDrive;
	
	public DrivingSys() {
		super();
		robotDrive = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR, 
				RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDriveJoystick());
    }
    
    public void drive(GenericHID moveStick, final int moveAxis,
    		GenericHID rotateStick, final int rotateAxis) {
    	robotDrive.arcadeDrive(moveStick, moveAxis, rotateStick, rotateAxis);
    }
    
    public void drive(double moveValue, double rotateValue) {
    	robotDrive.arcadeDrive(moveValue, rotateValue);
    }
    
    public void log() {
    	
    }
    
}

