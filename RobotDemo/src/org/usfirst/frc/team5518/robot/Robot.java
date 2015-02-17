
package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.framework.AutonomousChooser;
import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.function.ArmElevator;
import org.usfirst.frc.team5518.function.DriveTrain;
import org.usfirst.frc.team5518.function.PneumaticControl;
import org.usfirst.frc.team5518.function.SensorTrack;
import org.usfirst.frc.team5518.function.VisionTrack;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	public static XControllerOI xOi;
	public static JoystickOI jOi;
	
	private ArmElevator armElevator;
	private DriveTrain driveTrain;
	private PneumaticControl pneumaticControl;
	private SensorTrack sensorTrack;
	private VisionTrack visionTrack;
	
	private RobotFunction autonomousCommand;
	private SendableChooser autoChooser;
	
	private double auto_sec;
	private int autonomous_number;
	
    public void robotInit() {
    	
    	xOi = new XControllerOI();  // Xbox controller button mapping
    	jOi = new JoystickOI();  // Joystick button mapping class
    	
    	armElevator = new ArmElevator("ArmElevator");
    	driveTrain = new DriveTrain("DriveTrain");
    	pneumaticControl = new PneumaticControl("PneumaticControl");
    	sensorTrack = new SensorTrack("SensorTrack");
    	visionTrack = new VisionTrack("VisionTrack");
    	
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Autonomous 1: Push",
    			new AutonomousChooser(1));
    	autoChooser.addObject("Autonomous 2: Pull ",
    			new AutonomousChooser(2));
    	autoChooser.addObject("Autonomous 3: Push w/ Hill",
    			new AutonomousChooser(3));
    	autoChooser.addObject("Autonomous 4: Pull w/ Hill", 
    			new AutonomousChooser(4));
    	SmartDashboard.putData("Autonomous Chooser", autoChooser);
    	
    	armElevator.initialize();
    	driveTrain.initialize();
    	pneumaticControl.initialize();
    	sensorTrack.initialize();
    	visionTrack.initialize();
    	
    	driveTrain.setMaxPower(0.8);
    	driveTrain.setSensitivity(driveTrain.kDefaultSensitivity);
    	
    	SmartDashboard.putData(armElevator);
    	SmartDashboard.putData(driveTrain);
    	SmartDashboard.putData(pneumaticControl);
    	SmartDashboard.putData(sensorTrack);
    	SmartDashboard.putData(visionTrack);
    	SmartDashboard.putData("Autonomous Mode", autoChooser);
    	SmartDashboard.putData(Scheduler.getInstance());
    	
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	visionTrack.disabledInit();
    }
    
    public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
    
    public void autonomousInit() {
    	
    	auto_sec = Timer.getFPGATimestamp();
    	autonomous_number = ((AutonomousChooser) autoChooser.getSelected()).getNumber();
    	
    	switch (autonomous_number) {
    	case 1:
    	case 3:
    		autonomousSetup(false);
    		break;
    	case 2:
    	case 4:
    		autonomousSetup(true);
    		break;
    	}
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	double seconds = Timer.getFPGATimestamp();
    	
    	switch (autonomous_number) {
    	case 1:
    		autonomousPush(seconds, 2.0);
    		break;
    	case 2:
    		autonomousPull(seconds, 3.0);
    		break;
    	case 3:
    		autonomousPush(seconds, 3.5);
    		break;
    	case 4:
    		autonomousPull(seconds, 3.3);
    		break;
    	}
    	
    	output();
    	
    }
    
    public void teleopInit() {
    	
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
        armElevator.resetEncoder();
        visionTrack.telopInit();
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	Scheduler.getInstance().run();
    	
    	armElevator.setVictorSpeed((-Robot.xOi.getJoystick()
    			.getRawAxis(RobotMap.XBOX_LY_AXIS)*0.70), false);
    	armElevator.start();
    	driveTrain.start();
    	pneumaticControl.start();
    	sensorTrack.start();
    	visionTrack.start();
    	output();
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
    private void output() {
    	
    	armElevator.outputHandler();
    	driveTrain.outputHandler();
    	pneumaticControl.outputHandler();
    	sensorTrack.outputHandler();
    	//visionTrack.outputHandler();
    	
    	SmartDashboard.putNumber("System Clock", Timer.getFPGATimestamp());
		SmartDashboard.putNumber("Autonomous Init", auto_sec);
    	
    }
    
    private void autonomousSetup(boolean extra) {
    	
    	if (extra) {
    		armElevator.autoStart();
        	pneumaticControl.autonomous();
        	Timer.delay(1.0);
    	}
    	
    }
    
    private void autonomousPush(double seconds, double limit) {
    	if ((seconds - auto_sec) < limit)
    		driveTrain.drive(0, -0.5, 0);
    }
    
    private void autonomousPull(double seconds, double limit) {
    	
    	if ((seconds - auto_sec) < limit) {
    		pneumaticControl.autonomous();
    		armElevator.autoPeriodic();
    		driveTrain.drive(0, 0.5, 0);	
	    }
    	
    }
    
}
