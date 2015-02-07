package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmElevator extends RobotFunction {
	
	private Jaguar m_jaguar;
	private Encoder m_encoder;

	public ArmElevator(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_jaguar = new Jaguar(RobotMap.LIFT_MOTOR);
		m_jaguar.enableDeadbandElimination(true);
		m_encoder = new Encoder(RobotMap.ENCODER_DIO_PORTA, RobotMap.ENCODER_DIO_PORTB);
	}

	@Override
	public void start() {
	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	public void log() {
		super.log();
		SmartDashboard.putNumber("Jaguar Speed", m_jaguar.getSpeed());
		SmartDashboard.putNumber("Encoder Raw", m_encoder.getRaw());
	}
	
	// *********************** HELPER FUNCTIONS **************************
	
	public void setJaguarSpeed(double speed) {
		m_jaguar.set(speed);
	}
	

}
