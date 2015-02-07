package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain extends RobotFunction {
	
	public static final double kDefaultSensitivity = RobotDrive.kDefaultSensitivity;

	private RobotDrive m_robot;

	public DriveTrain(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_robot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
				RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
		m_robot.setExpiration(RobotDrive.kDefaultExpirationTime);  // set expiration time for motor movement if error occurs
		m_robot.setSafetyEnabled(true);  // enable safety so motors don't burn out
	}

	@Override
	public void start() {
		m_robot.mecanumDrive_Cartesian(-Robot.jOi.getJoystick().getX(),
				-Robot.jOi.getJoystick().getTwist(), 
				-Robot.jOi.getJoystick().getY(), 0);
	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	protected void log() {
		super.log();
	}
	
	// *********************** HELPER FUNCTIONS **************************
	
	public void setMaxPower(double power) {
		m_robot.setMaxOutput(power);
	}
	
	public void setSensitivity(double sensitivity) {
		m_robot.setSensitivity(sensitivity);
	}

}
