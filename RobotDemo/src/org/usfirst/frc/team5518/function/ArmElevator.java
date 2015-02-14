package org.usfirst.frc.team5518.function;

import org.usfirst.frc.team5518.framework.RobotFunction;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmElevator extends RobotFunction {
	
	private Victor m_victor;
	private Encoder m_encoder;
	private DigitalInput m_maxLimit;
	
	private Counter max_counter;
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
		
		m_maxLimit = new DigitalInput(RobotMap.LIMIT_DIO_PORT1);
		max_counter = new Counter(m_maxLimit);
		
	}

	@Override
	public void start() {
		
		if (Robot.xOi.getJoystickBtn(RobotMap.XBOX_BTN_X).get()) {
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
		SmartDashboard.putNumber("1-Max. Limit Switch", max_counter.get());
		SmartDashboard.putBoolean("2-Max. Limit Switch", isMaxSwitchSet());
		SmartDashboard.putBoolean("3-Max. Limit Switch", m_maxLimit.get());
	}
	
	// *********************** HELPER FUNCTIONS **************************
	
	public void setVictorSpeed(double speed) {
		
		if (speed == 0.0)  // if speed value is 0
			this.victor_speed = 0.06;  // set a default value
		else  // otherwise
			this.victor_speed = speed;  // set the given value
		
		m_victor.set(victor_speed);  // set the victor speed
	}
	
	public void resetEncoder() {
		m_encoder.reset();  // reset the encoder
	}
	
	private boolean isMaxSwitchSet() {
		// return true/valse for limit switch value
		return max_counter.get() > 0;
	}
	
}
