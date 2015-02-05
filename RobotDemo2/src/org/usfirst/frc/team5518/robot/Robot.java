
package org.usfirst.frc.team5518.robot;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

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
public class Robot extends SampleRobot {
	
    RobotDrive myRobot;
    Joystick stick;
    private DoubleSolenoid solenoid;
    private Compressor compressor;
    private Jaguar jaguar;

    public Robot() {
        myRobot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
				RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
        myRobot.setExpiration(0.1);
        stick = new Joystick(RobotMap.JOYSTICK_PORT1);
        compressor = new Compressor();
        solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1,
        		RobotMap.SOLENOID_PCM_PORT2);
        jaguar = new Jaguar(RobotMap.LIFT_MOTOR);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
        myRobot.setSafetyEnabled(false);
        myRobot.drive(-0.5, 0.0);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        myRobot.drive(0.0, 0.0);	// stop robot
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        myRobot.setMaxOutput(0.75);
        compressor.setClosedLoopControl(true);
        jaguar.enableDeadbandElimination(true);
        jaguar.set(0.1);
        
        while (isOperatorControl() && isEnabled()) {
        	myRobot.mecanumDrive_Cartesian(-stick.getX(),
    				-stick.getY(), 
    				stick.getTwist(), 0);
        	leftTriggerCheck();
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
    
    /**
     * Polls Xbox Controller Left Trigger Axis Value
     */
    private void leftTriggerCheck() {
    	if (stick.getRawAxis(RobotMap.XBOX_LT_AXIS) > 0.5d) {  // if left trigger pressed more than halfway
    		solenoid.set(DoubleSolenoid.Value.kForward);  // open solenoid valves to make pistons go forward
    	}else if (stick.getRawAxis(RobotMap.XBOX_LT_AXIS) <= 0.5d) {  // if left trigger pressed less or equal to halfway
    		solenoid.set(DoubleSolenoid.Value.kReverse);  // open solenoid values to make pistons go backward
    	} else {  // otherwise
    		solenoid.set(DoubleSolenoid.Value.kOff);  // close both solenoid valves and prevent air from flowing
    	}
    }
}
