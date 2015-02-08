package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorTrack extends RobotFunction {
	
	private Ultrasonic m_sensor;
	//private AnalogOutput m_sensor;
	
	public SensorTrack(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_sensor = new Ultrasonic(RobotMap.SENSOR_DIO_PORT1,
				RobotMap.SENSOR_DIO_PORT2);
	}

	@Override
	public void start() {

	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	protected void log() {
		super.log();
		SmartDashboard.putNumber("Sensor in mm", m_sensor.getRangeMM());
		SmartDashboard.putNumber("Sensor in inches", m_sensor.getRangeInches());
	}

}
