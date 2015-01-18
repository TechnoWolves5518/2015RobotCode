 
package org.usfirst.frc.team5518.robot;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot { // SampleRobot doesn't call functions periodically
	
    private RobotDrive myRobot;
    private Joystick stick;
    
    private PowerDistributionPanel pdp;
    
    private DoubleSolenoid solenoid;
    private Compressor compressor;
    
    public Robot() {	// initialize variables in constructor
    	stick = new Joystick(RobotMap.JOYSTICK_PORT); // set the stick to refer to joystick #0    	
        myRobot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
        		RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
        myRobot.setExpiration(RobotDrive.kDefaultExpirationTime);
        
        pdp = new PowerDistributionPanel();
        
        compressor = new Compressor(); // Compressor is controlled automatically by PCM
        
        solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2); // PCM port #0 & #1
        /*
         * solenoid.set(DoubleSolenoid.Value.kOff);
         * solenoid.set(DoubleSolenoid.Value.kForward);
         * solenoid.set(DoubleSolenoid.Value.kReverse);
         */
        
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {	// function for autonomous mode
        myRobot.setSafetyEnabled(false);
        myRobot.drive(-0.5, 0.0);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        myRobot.drive(0.0, 0.0);	// stop robot
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {	// function for teleop mode
        myRobot.setSafetyEnabled(true);
        compressor.setClosedLoopControl(true);
        
        while (isOperatorControl() && isEnabled()) {
            myRobot.arcadeDrive(-stick.getY()/RobotMap.SENSITIVITY_BUFFER, -stick.getX()/RobotMap.SENSITIVITY_BUFFER); // drive with arcade style (use right stick)
            LiveWindow.run();
            Timer.delay(0.005);		// wait for a motor update time
            log();
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    	while (isTest() && isEnabled()) {
    		LiveWindow.run();
        	Timer.delay(0.005);
    	}
    }
    
    private void log() {
    	SmartDashboard.putNumber("PDP Current", pdp.getTotalCurrent()); // log current in PDP
    	SmartDashboard.putNumber("PDP Temperature", pdp.getTemperature()); // log temperature in PDP
    }
}
