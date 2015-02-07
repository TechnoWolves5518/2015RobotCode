package org.usfirst.frc.team5518.framework;

import java.util.Enumeration;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.IllegalUseOfCommandException;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;


public abstract class RobotFunction implements NamedSendable {
	
	private String m_name;  // name of this command
	private ITable m_table;
	
	public RobotFunction() {
		m_name = getClass().getName();
		m_name = m_name.substring(m_name.lastIndexOf('.') + 1);
	}
	
	public RobotFunction(String name) {
		m_name = name;
	}
	
	public abstract void initialize();
	public abstract void start();
	public abstract void outputHandler();
	
	protected void log() {
		SmartDashboard.putData(this);
	}
	
	protected void assertVal() {
		
	}
	
	public String toString() {
		return m_name;
	}
	
	public String getName() {
        return m_name;
    }

    public String getSmartDashboardType() {
        return "Subsystem";
    }
    
    public void initTable(ITable table) {
        m_table = table;
        /*if(table!=null) {
            if (defaultCommand != null) {
                table.putBoolean("hasDefault", true);
                table.putString("default", defaultCommand.getName());
            } else {
                table.putBoolean("hasDefault", false);
            }
            if (currentCommand != null) {
                table.putBoolean("hasCommand", true);
                table.putString("command", currentCommand.getName());
            } else {
                table.putBoolean("hasCommand", false);
            }
        } */
        table.putBoolean("hasDefault", false);
        table.putBoolean("hasCommand", false);
    }

    public ITable getTable() {
        return m_table;
    }
    
}
