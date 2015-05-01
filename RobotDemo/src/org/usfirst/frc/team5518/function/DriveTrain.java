package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends RobotFunction {
	
	public static final double kDefaultSensitivity = 0.70;

	private RobotDrive m_robot;
	
	private float speed_factor;
	
	public DriveTrain(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_robot = new RobotDrive(RobotMap.FRONT_LEFT_MOTOR, RobotMap.REAR_LEFT_MOTOR,
				RobotMap.FRONT_RIGHT_MOTOR, RobotMap.REAR_RIGHT_MOTOR);
		m_robot.setExpiration(RobotDrive.kDefaultExpirationTime);  // set expiration time for motor movement if error occurs
		m_robot.setSafetyEnabled(true);  // enable safety so motors don't burn out
		m_robot.setInvertedMotor(MotorType.kFrontRight, true);
		//m_robot.setInvertedMotor(MotorType.kFrontLeft, true);
		m_robot.setInvertedMotor(MotorType.kRearRight, true);
		//m_robot.setInvertedMotor(MotorType.kRearLeft, true);
		
	}

	@Override
	public void start() {
		
		if (Robot.jOi.getJoystick().getRawButton(RobotMap.BTN_TRIGGER))
			speed_factor = 0.35f;
		else
			speed_factor = 0.85f;
		
		m_robot.mecanumDrive_Cartesian(Robot.jOi.getJoystick().getRawAxis(RobotMap.X_AXIS)*speed_factor,
				Robot.jOi.getJoystick().getRawAxis(RobotMap.Y_AXIS)*speed_factor, 
				Robot.jOi.getJoystick().getRawAxis(RobotMap.Z_AXIS)*speed_factor, 0);
		
	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	protected void log() {
		super.log();
		SmartDashboard.putNumber("Joystick X Axis", Robot.jOi.getJoystick().getRawAxis(RobotMap.X_AXIS));
		SmartDashboard.putNumber("Joystick Y Axis", Robot.jOi.getJoystick().getRawAxis(RobotMap.Y_AXIS));
		SmartDashboard.putNumber("Joystick Z Axis", Robot.jOi.getJoystick().getRawAxis(RobotMap.Z_AXIS));
	}
	
	
	// *********************** HELPER FUNCTIONS **************************
	
	/**
	 * Set maximum power of RobotDrive system
	 */
	public void setMaxPower(double power) {
		m_robot.setMaxOutput(power);
	}
	
	/*
	 * Set turn sensitivity/radius of RobotDrive system
	 */
	public void setSensitivity(double sensitivity) {
		m_robot.setSensitivity(sensitivity);
	}
	
	public void drive(double x, double y, double rotation) {
		m_robot.mecanumDrive_Cartesian(x, y, rotation, 0);
	}

}
