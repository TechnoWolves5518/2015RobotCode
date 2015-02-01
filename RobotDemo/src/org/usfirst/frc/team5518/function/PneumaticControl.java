package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PneumaticControl extends RobotFunction {
	
	private Compressor m_compressor;
	private DoubleSolenoid m_solenoid;

	public PneumaticControl(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_compressor = new Compressor();
		m_compressor.setClosedLoopControl(true);
		m_solenoid = new DoubleSolenoid(RobotMap.SOLENOID_PCM_PORT1, RobotMap.SOLENOID_PCM_PORT2);
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
