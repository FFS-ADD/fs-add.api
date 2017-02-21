package com.accenture.fsadd.extif.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

/**
 * 
 * Control the external-if runner
 *
 */
public class ExtIfRunnerControlCommand {
	
	/**
	 * 
	 * Command to control the external-if running
	 *
	 */
	public static enum Command{
		/**
		 * <p>Command to control the external-if running</p>
		 * <ul>
		 * <li>OPEN:External if can run.</li>
		 * <li>STOP:External if should be stopped.</li>
		 * </ul>
		 *
		 */
		OPEN, STOP	
	}
	
	
	/**
	 * Name of external name
	 */
	@Id
	private String extIfName;
	
	/**
	 * Command
	 */
	private Command command;
	
	/**
	 * Time of command issued
	 */
	private LocalDateTime issuedOn;
	
	public String getExtIfName() {
		return extIfName;
	}

	public void setExtIfName(String extIfName) {
		this.extIfName = extIfName;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public LocalDateTime getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(LocalDateTime issuedOn) {
		this.issuedOn = issuedOn;
	}
	
	
	
}
