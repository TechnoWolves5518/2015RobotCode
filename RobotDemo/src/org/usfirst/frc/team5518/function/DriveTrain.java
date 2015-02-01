package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain extends RobotFunction {

	private RobotDrive m_robot;

	public DriveTrain(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_robot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
				RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
		m_robot.setExpiration(RobotDrive.kDefaultExpirationTime);  // set expiration time for motor movement if error occurs
		m_robot.setSafetyEnabled(true);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

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
