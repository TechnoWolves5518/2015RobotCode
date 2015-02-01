package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;

public class VisionTrack extends RobotFunction {
	
	private Image frame;
	private int session;
	private NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

	public VisionTrack(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);  // create the image frame for cam
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);  // get reference to camera
        NIVision.IMAQdxConfigureGrab(session);  // grab current streaming session
        NIVision.IMAQdxStartAcquisition(session);  // start video capturing from camera
	}

	@Override
	public void start() {
		NIVision.IMAQdxGrab(session, frame, 1);  // grab frame from session
        /*NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f); // draw rectangle on frame*/
        
        CameraServer.getInstance().setImage(frame);  // send frame to stream in SmartDashboard
	}

	@Override
	protected void inputHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void outputHandler() {
		// TODO Auto-generated method stub

	}

}
