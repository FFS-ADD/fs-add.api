package com.accenture.fsadd.extif.service;

import com.accenture.fsadd.extif.ExtIfName;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand.Command;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus.Status;
import com.accenture.fsadd.extif.entity.ExtIfSetting;

/**
 * External If Service
 * 
 */
public interface ExtIfService {

	/**
	 * Update external-if executed status
	 * 
	 * @param extIfName
	 *            External-if Name
	 * @param status
	 *            Status
	 * @param message
	 *            message
	 */
	void updateExtIfStatus(ExtIfName extIfName, Status status, String message);

	/**
	 * Query external-if status
	 * 
	 * @param extIfName
	 *            External-If name
	 * @return external-if status
	 */
	ExtIfRunnerStatus queryExtIfStatus(ExtIfName extIfName);

	/**
	 * Issue control command
	 * 
	 * @param extIfName
	 *            External-If name
	 * @param command
	 *            control command
	 */
	void issueControlComand(ExtIfName extIfName, Command command);

	/**
	 * Query control command
	 * 
	 * @param extIfName
	 *            External-If name
	 * @return Control Command
	 */
	ExtIfRunnerControlCommand getControlCommand(ExtIfName extIfName);
	
	/**
	 * Save the external if setting
	 * @param extIfSetting external Interface Setting
	 */
	void saveExtIfSetting(ExtIfSetting extIfSetting);
	
	/**
	 * Get external Interface Setting
	 * @return
	 */
	ExtIfSetting getExtIfSetting();
	
	

}
