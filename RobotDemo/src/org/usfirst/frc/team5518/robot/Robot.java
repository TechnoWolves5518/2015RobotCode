
package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.function.ArmElevator;
import org.usfirst.frc.team5518.function.DriveTrain;
import org.usfirst.frc.team5518.function.PneumaticControl;
import org.usfirst.frc.team5518.function.SensorTrack;
import org.usfirst.frc.team5518.function.VisionTrack;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	private ArmElevator armElevator;
	private DriveTrain driveTrain;
	private PneumaticControl pneumaticControl;
	private SensorTrack sensorTrack;
	private VisionTrack visionTrack;
	
    public void robotInit() {
    	
    	armElevator = new ArmElevator("ArmElevator");
    	driveTrain = new DriveTrain("DriveTrain");
    	pneumaticControl = new PneumaticControl("PneumaticControl");
    	sensorTrack = new SensorTrack("SensorTrack");
    	visionTrack = new VisionTrack("VisionTrack");
    	
    	armElevator.initialize();
    	driveTrain.initialize();
    	pneumaticControl.initialize();
    	sensorTrack.initialize();
    	visionTrack.initialize();
    	
    	SmartDashboard.putData(Scheduler.getInstance());
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	armElevator.start();
    	driveTrain.start();
    	pneumaticControl.start();
    	sensorTrack.start();
    	visionTrack.start();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
