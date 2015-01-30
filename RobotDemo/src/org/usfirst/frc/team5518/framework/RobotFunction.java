package org.usfirst.frc.team5518.framework;


public abstract class RobotFunction {
	
	private String m_name;  // name of this command
	
	public RobotFunction() {
		m_name = getClass().getName();
		m_name = m_name.substring(m_name.lastIndexOf('.') + 1);
	}
	
	protected abstract void initialize();
	protected abstract void start();
	protected abstract void log();
	protected abstract void inputHandler();
	protected abstract void outputHandler();
	
	protected void assertVal() {
		
	}
	
}
