package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorTrack extends RobotFunction {
	
	//private Ultrasonic m_sensor;
	private AnalogInput m_sensor;
	private BuiltInAccelerometer m_accel;
	
	public SensorTrack(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		
		m_sensor = new AnalogInput(RobotMap.SENSOR_ANALOG_PORT);
		/*m_sensor = new Ultrasonic(RobotMap.SENSOR_DIO_PORT2,
				RobotMap.SENSOR_DIO_PORT1);
		m_sensor.setEnabled(true);*/
		m_accel = new BuiltInAccelerometer();
		
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
		
		// 4.88 mV scaling factor to yield measurement in mm
		SmartDashboard.putNumber("Sensor in mm", m_sensor.getVoltage() * 0.00488);
		SmartDashboard.putNumber("Sensor count", m_sensor.getAccumulatorCount());
		SmartDashboard.putNumber("Sensor value", m_sensor.getAccumulatorValue());
		
		/*SmartDashboard.putNumber("Sensor in mm", m_sensor.getRangeMM());
		SmartDashboard.putNumber("Sensor in inches", m_sensor.getRangeInches());*/
		
		SmartDashboard.putNumber("Accelerometer X", m_accel.getX());
		SmartDashboard.putNumber("Accelerometer Y", m_accel.getY());
		SmartDashboard.putNumber("Accelerometer Z", m_accel.getZ());
		
	}

}
