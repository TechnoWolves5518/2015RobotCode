package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmElevator extends RobotFunction {
	
	private Victor m_victor;
	private Encoder m_encoder;
	//private DigitalInput m_maxLimit;
	//private DigitalInput m_minLimit;
	
	//private Counter max_counter;
	//private Counter min_counter;
	private double victor_speed;
	private int victor_state = 0;

	public ArmElevator(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		m_victor = new Victor(RobotMap.LIFT_MOTOR);
		m_victor.enableDeadbandElimination(true);
		m_victor.setExpiration(Victor.DEFAULT_SAFETY_EXPIRATION);
		m_encoder = new Encoder(RobotMap.ENCODER_DIO_PORTA,
				RobotMap.ENCODER_DIO_PORTB, true);
		//max_counter = new Counter(m_maxLimit);
		//min_counter = new Counter(m_minLimit);
	}

	@Override
	public void start() {
		
		if (Robot.xOi.getJoystickBtn(RobotMap.XBOX_BTN_A).get()) {
			switch (victor_state) {
			 case 0:
				 m_victor.disable();
				 victor_state = 1;
				 break;
			 case 1:
				 m_victor.set(victor_speed);
				 victor_state = 0;
			 }
		}
		
		
	}

	@Override
	public void outputHandler() {
		log();
	}
	
	@Override
	public void log() {
		super.log();
		SmartDashboard.putNumber("Victor Speed", m_victor.getSpeed());
		SmartDashboard.putNumber("Encoder Raw", m_encoder.getRaw());
		SmartDashboard.putNumber("Encoder Distance", m_encoder.getDistance());
		SmartDashboard.putNumber("Encoder Rate", m_encoder.getRate());
		SmartDashboard.putNumber("Encoder Count", m_encoder.get());
		/*SmartDashboard.putNumber("Min. Limit Switch", min_counter.get());
		SmartDashboard.putBoolean("Min. Limit Switch", isMinSwitchSet());
		SmartDashboard.putNumber("Max. Limit Switch", max_counter.get());
		SmartDashboard.putBoolean("Max. Limit Switch", isMaxSwitchSet());*/
	}
	
	// *********************** HELPER FUNCTIONS **************************
	
	public void setJaguarSpeed(double speed) {
		this.victor_speed = speed;
		m_victor.set(speed);
	}
	
	public void resetEncoder() {
		m_encoder.reset();
	}
	
	/*private boolean isMaxSwitchSet() {
		return max_counter.get() > 0;
	}
	
	private boolean isMinSwitchSet() {
		return min_counter.get() > 0;
	}*/

}
