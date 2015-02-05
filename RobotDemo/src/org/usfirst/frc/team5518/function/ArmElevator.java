package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;

public class ArmElevator extends RobotFunction {
	
	private Jaguar m_jaguar;

	public ArmElevator(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_jaguar = new Jaguar(RobotMap.LIFT_MOTOR);
		m_jaguar.enableDeadbandElimination(true);
	}

	@Override
	public void start() {
	}

	@Override
	protected void outputHandler() {

	}
	
	public void setJaguarSpeed(double speed) {
		m_jaguar.set(speed);
	}

}
