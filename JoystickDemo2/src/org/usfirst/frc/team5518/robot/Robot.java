
package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5518.robot.commands.ArcadeDriveJoystick;
import org.usfirst.frc.team5518.robot.commands.AutonomousCmd;
import org.usfirst.frc.team5518.robot.subsystems.AutonomousSys;
import org.usfirst.frc.team5518.robot.subsystems.DrivingSys;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static AutonomousSys autoSys;
	public static DrivingSys driveSys;
	public static OI oi;
	
	private PowerDistributionPanel pdp;
	private Compressor compressor;
	private DoubleSolenoid solenoid;
	private CameraServer camera;

    Command autonomousCommand;
    Command arcadeDriveCmd;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	// initialize all subsystems and important classes
		oi = new OI();
		//autoSys = new AutonomousSys();
		driveSys = new DrivingSys();
		
        // instantiate the command used for the autonomous period
        //autonomousCommand = new AutonomousCmd();
        
        // instantiate cmd used for teleop period
        arcadeDriveCmd = new ArcadeDriveJoystick();
        
        // Show what cmd the susbsystem is running on SmartDashboard
        SmartDashboard.putData(driveSys);
        
        // Initialize Power Distribution Panel
        pdp = new PowerDistributionPanel();
        
        // Compressor is controlled automatically by PCM
        compressor = new Compressor();
        
        solenoid = new DoubleSolenoid(0, 1);
        solenoid.set(DoubleSolenoid.Value.kReverse);
        
        /*camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture("cam0");*/
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        driveSys.log();
        log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        compressor.setClosedLoopControl(true);
        
        // start command for driving in telop
        arcadeDriveCmd.start();
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driveSys.log();
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        driveSys.log();
        log();
    }
    
    private void log() {
    	SmartDashboard.putNumber("PDP Current", pdp.getTotalCurrent()); // log current in PDP
    	SmartDashboard.putNumber("PDP Temperature", pdp.getTemperature()); // log temperature in PDP
    	SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage()); // log voltage in PDP
    	SmartDashboard.putNumber("PDP Total Current", pdp.getTotalCurrent()); // total current in PDP
    	SmartDashboard.putNumber("PDP Total Energy", pdp.getTotalEnergy()); // total energy in PDP
    	SmartDashboard.putNumber("PDP Total Power", pdp.getTotalPower()); // total power in PDP
    }
    
}
