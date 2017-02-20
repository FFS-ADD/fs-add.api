package com.accenture.fsadd.extif.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.extif.ExtIfName;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand.Command;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus.Status;
import com.accenture.fsadd.extif.entity.ExtIfSetting;
import com.accenture.fsadd.extif.repository.ExtIfRunnerControlCommandRepository;
import com.accenture.fsadd.extif.repository.ExtIfRunnerStatusRepository;
import com.accenture.fsadd.extif.repository.ExtIfSettingRepository;
import com.accenture.fsadd.extif.service.ExtIfService;

@Service
public class ExtIfServiceImpl implements ExtIfService {

	/**
	 * External Interface Setting
	 */
	private static final String EXT_IF_SETTING_KEY="ExtIfSetting";
	
	@Autowired
	private ExtIfRunnerStatusRepository extIfRunnerStatusRepository;

	@Autowired
	private ExtIfRunnerControlCommandRepository extIfRunnerControllerRepository;
	
	@Autowired
	private ExtIfSettingRepository extIfSettingRepository;

	@Override
	public void updateExtIfStatus(ExtIfName extIfName, Status status, String message) {
		// Get the existed Entity if it exists
		ExtIfRunnerStatus extIfRunnerStatus = extIfRunnerStatusRepository.findOne(extIfName.toString());
		if (extIfRunnerStatus == null) {
			extIfRunnerStatus = new ExtIfRunnerStatus();
		}
		extIfRunnerStatus.setExtIfName(extIfName.toString());
		extIfRunnerStatus.setStatus(status);
		extIfRunnerStatus.setMessage(message);

		// Update to Processing, it will clear the starton items
		if (Status.PROCESSING == status) {
			extIfRunnerStatus.setEndedOn(null);
			extIfRunnerStatus.setStartedOn(LocalDateTime.now());
		}

		if (Status.COMPLETED == status) {
			extIfRunnerStatus.setEndedOn(LocalDateTime.now());
			extIfRunnerStatus.setLastExecutedOn(extIfRunnerStatus.getStartedOn());

		}
		if (Status.ERROR == status) {
			extIfRunnerStatus.setEndedOn(LocalDateTime.now());
		}
		extIfRunnerStatusRepository.save(extIfRunnerStatus);

	}

	@Override
	public ExtIfRunnerStatus queryExtIfStatus(ExtIfName extIfName) {
		return extIfRunnerStatusRepository.findOne(extIfName.toString());
	}

	@Override
	public void issueControlComand(ExtIfName extIfName, Command command) {
		ExtIfRunnerControlCommand controllerCommand = new ExtIfRunnerControlCommand();
		controllerCommand.setCommand(command);
		controllerCommand.setExtIfName(extIfName.toString());
		controllerCommand.setIssuedOn(LocalDateTime.now());
		extIfRunnerControllerRepository.save(controllerCommand);
	}

	@Override
	public ExtIfRunnerControlCommand getControlCommand(ExtIfName extIfName) {
		return extIfRunnerControllerRepository.findOne(extIfName.toString());

	}

	@Override
	public void saveExtIfSetting(ExtIfSetting extIfSetting) {
		extIfSetting.setId(EXT_IF_SETTING_KEY);
		extIfSettingRepository.save(extIfSetting);
	}

	@Override
	public ExtIfSetting getExtIfSetting() {
		return extIfSettingRepository.findOne(EXT_IF_SETTING_KEY);
	}

}
