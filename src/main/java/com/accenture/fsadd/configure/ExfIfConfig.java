package com.accenture.fsadd.configure;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.accenture.fsadd.extif.ExtIfRunner;

@Configuration
public class ExfIfConfig {
	
	@Bean
	public ExtIfRunner getExtIfRunner(){
		return new ExtIfRunner();
	}
	
	@Bean
	@Autowired
	public ThreadPoolTaskScheduler getThreadPoolTaskScheduler(ExtIfRunner runner){
		
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		Date startTime = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
		scheduler.scheduleAtFixedRate(runner, startTime, 10*60*1000);
		return scheduler;
		
	}
	

}
