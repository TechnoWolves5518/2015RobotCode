package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		m_robot.setInvertedMotor(MotorType.kFrontRight, true);
		//m_robot.setInvertedMotor(MotorType.kFrontLeft, true);
		m_robot.setInvertedMotor(MotorType.kRearRight, true);
		//m_robot.setInvertedMotor(MotorType.kRearLeft, true);
	}

	@Override
	public void start() {
		m_robot.mecanumDrive_Cartesian(Robot.jOi.getJoystick().getRawAxis(RobotMap.X_AXIS),
				Robot.jOi.getJoystick().getRawAxis(RobotMap.Y_AXIS), 
				Robot.jOi.getJoystick().getRawAxis(RobotMap.Z_AXIS), 0);
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
	
	public void setMaxPower(double power) {
		m_robot.setMaxOutput(power);
	}
	
	public void setSensitivity(double sensitivity) {
		m_robot.setSensitivity(sensitivity);
	}

}
