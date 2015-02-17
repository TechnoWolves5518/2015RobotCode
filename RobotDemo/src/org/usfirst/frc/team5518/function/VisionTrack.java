package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

public class VisionTrack extends RobotFunction {
	
	private Image frame;
	private int session;

	public VisionTrack(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);  // create the image frame for cam
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);  // get reference to camera
        NIVision.IMAQdxConfigureGrab(session);  // grab current streaming session
	}

	@Override
	public void start() {
		
		NIVision.IMAQdxGrab(session, frame, 1);  // grab frame from session
		CameraServer.getInstance().setImage(frame);  // send frame to stream in SmartDashboard
		
	}

	@Override
	public void outputHandler() {

	}
	
	public void telopInit() {
		NIVision.IMAQdxStartAcquisition(session);  // start video capturing from camera
	}
	
	public void disabledInit() {
		NIVision.IMAQdxStopAcquisition(session);
	}

}
