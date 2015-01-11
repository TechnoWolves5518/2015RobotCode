
package org.usfirst.frc.team5518.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.subsystems.AutonomousSys;

/**
 *
 */
public class AutonomousCmd extends Command {
	
	private int autoLoopCounter;

    public AutonomousCmd() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.autoSys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	autoLoopCounter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (autoLoopCounter < 100) {
    		Robot.autoSys.drive();
    		autoLoopCounter++;
    	} else {
    		Robot.autoSys.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
