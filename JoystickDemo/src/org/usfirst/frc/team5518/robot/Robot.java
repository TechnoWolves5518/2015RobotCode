 
package org.usfirst.frc.team5518.robot;


import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
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
    
    //private CameraServer camera;
    private int session;
    private Image frame;
    private NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
    
    public Robot() {	// initialize variables in constructor
    	stick = new Joystick(RobotMap.JOYSTICK_PORT); // set the stick to refer to joystick #0    	
        myRobot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
        		RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
        myRobot.setExpiration(RobotDrive.kDefaultExpirationTime);  // set expiration time for motor movement if error occurs
        
        pdp = new PowerDistributionPanel();  // instantiate class to get PDP values
        
        compressor = new Compressor(); // Compressor is controlled automatically by PCM
        
        solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2); // solenoid on PCM port #0 & #1
        
        /*camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.setSize(200);*/
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);  // create the image frame for cam
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);  // get reference to camera
        NIVision.IMAQdxConfigureGrab(session);  // grab current streaming session
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
        myRobot.setSafetyEnabled(true);  // enable safety on robot motors to prevent burning out
        compressor.setClosedLoopControl(true);  // enable the compressor
        NIVision.IMAQdxStartAcquisition(session);  // start video capturing from camera
        //camera.startAutomaticCapture("cam0");
        
        while (isOperatorControl() && isEnabled()) {  // loop while robot is enabled in Teleop mdoe
        	LiveWindow.run();  // run LiveWindow - unsure if works
        	log();  // log values onto SmartDashboard
        	
        	// Drive robot in Arcade Drive by inverting controller axis and reducing sensitivity w/ left stick of Xbox Controller or joystick
            myRobot.arcadeDrive(-stick.getY()/RobotMap.SENSITIVITY_BUFFER, -stick.getX()/RobotMap.SENSITIVITY_BUFFER);
            
            leftTriggerCheck(); // function to check whether trigger is pressed
            cameraProcess(); // function to grab session frame
            
            Timer.delay(0.005);  // wait for a motor update time to avoid overloading processor
        }
        
        NIVision.IMAQdxStopAcquisition(session);  // stop acquiring video capture when disabled or loop termination
    }

    /**
     * Runs during test mode
     */
    public void test() {
    	while (isTest() && isEnabled()) {  // loop while robot is enabled in test mode
    		LiveWindow.run();  // run LiveWindow - unsure if works
        	Timer.delay(0.005);  // wait for a motor update time to avoid overloading processor
    	}
    }
    
    /**
     * Logs values onto SmartDashboard
     */
    private void log() {
    	SmartDashboard.putNumber("PDP Current", pdp.getTotalCurrent()); // log current in PDP
    	SmartDashboard.putNumber("PDP Temperature", pdp.getTemperature()); // log temperature in PDP
    }
    
    /**
     * Polls Xbox Controller Left Trigger Axis Value
     */
    private void leftTriggerCheck() {
    	if (stick.getRawAxis(2) > 0.5d) {  // if left trigger pressed more than halfway
    		solenoid.set(DoubleSolenoid.Value.kForward);  // open solenoid valves to make pistons go forward
    	}else if (stick.getRawAxis(2) <= 0.5d) {  // if left trigger pressed less or equal to halfway
    		solenoid.set(DoubleSolenoid.Value.kReverse);  // open solenoid values to make pistons go backward
    	} else {  // otherwise
    		solenoid.set(DoubleSolenoid.Value.kOff);  // close both solenoid valves and prevent air from flowing
    	}
    }
    
    /**
     * Process video stream frame-by-frame and update the steam
     */
    private void cameraProcess() {
		NIVision.IMAQdxGrab(session, frame, 1);  // grab frame from session
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f); // draw rectangle on frame
        
        CameraServer.getInstance().setImage(frame);  // send frame to stream in SmartDashboard
	}
    }
;