package com.accenture.fsadd.extif;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand.Command;
import com.accenture.fsadd.extif.entity.ExtIfRunnerSetting;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus.Status;
import com.accenture.fsadd.extif.entity.ExtIfSetting;
import com.accenture.fsadd.extif.service.ExtIfService;

public class ExtIfRunner implements Runnable {

	private static Logger log = LoggerFactory.getLogger(ExtIfRunner.class);

	@Autowired
	private ExtIfService extIfService;

	private ThreadPoolTaskScheduler scheduler;

	private Map<ExtIfName, ExtIfDataExtrator> extIfExtratorMap = new LinkedHashMap<>();

	private Map<ExtIfName, ExtIfDataMapper> extIfMapperMap = new LinkedHashMap<>();
	
	private ExtIfRunnerSetting extIfRunnerSetting;
	
	@PostConstruct
	public void start() {
		// Get Setting
		ExtIfSetting extIfSetting = extIfService.getExtIfSetting();
		extIfRunnerSetting = extIfSetting.getExtIfRunnerSetting();
		// Open the external data extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
			extIfService.issueControlComand(extIfName, Command.OPEN);
			extIfService.updateExtIfStatus(extIfName, Status.STOPPED, "restart");
		});
		extIfMapperMap.forEach((extIfName, extIfDataExtrator) -> {
			extIfService.issueControlComand(extIfName, Command.OPEN);
		});
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		CronTrigger ct = new CronTrigger(extIfRunnerSetting.getCronExpression());
		scheduler.schedule(this, ct);
	}

	@PreDestroy
	public void stop() {
		// Close the external data extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
			extIfService.issueControlComand(extIfName, Command.STOP);
		});
		extIfMapperMap.forEach((extIfName, extIfDataExtrator) -> {
			extIfService.issueControlComand(extIfName, Command.STOP);
		});
		scheduler.shutdown();

	}

	public void registerExtIf(ExtIfName extIfName, ExtIfDataExtrator extrator, ExtIfDataMapper mapper) {
		extIfExtratorMap.put(extIfName, extrator);
		extIfMapperMap.put(extIfName, mapper);

	}

	public void run() {
		log.info("ExtIf Runner Start");
		// Execute the extrator and mapper
		extIfExtratorMap.forEach((extIfName, extIfDataExtrator) -> {
			try {
				ExtIfRunnerStatus status = extIfService.queryExtIfStatus(extIfName);
				ExtIfRunnerControlCommand command = extIfService.getControlCommand(extIfName);
				// When error is occcurred in last execution or there are stop
				// command received data exetrator or mapper will not be
				// executed
				if (status.getStatus() != Status.ERROR && command.getCommand() != Command.STOP) {
					log.info("{} external if started.lastExecuted={}", extIfName, status.getStartedOn());
					// Updating updateExtIfStatus
					extIfService.updateExtIfStatus(extIfName, Status.PROCESSING, "Start from ExtIfRunner");
					if (extIfDataExtrator != null) {
						extIfDataExtrator.extrator(status.getLastExecutedOn());
					}
					ExtIfDataMapper mapper = extIfMapperMap.get(extIfName);
					if (mapper != null) {
						mapper.map(status.getLastExecutedOn());
					}
					extIfService.updateExtIfStatus(extIfName, Status.COMPLETED, "ended from ExtIfRunner");
					log.info("{} external if successed.", extIfName);
				}

			} catch (Exception e) {
				extIfService.updateExtIfStatus(extIfName, Status.ERROR, "ended from ExtIfRunner");
				log.error("ExtIf Runner error.", e);
			}
		});
		log.info("ExtIf Runner End");

	}


	public ExtIfService getExtIfService() {
		return extIfService;
	}

	public void setExtIfService(ExtIfService extIfService) {
		this.extIfService = extIfService;
	}

}
