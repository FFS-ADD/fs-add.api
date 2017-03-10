package com.accenture.fsadd.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.fsadd.extif.ExtIfName;
import com.accenture.fsadd.extif.ExtIfRunner;
import com.accenture.fsadd.extif.redmine.DefaultRedmineDataExtrator;
import com.accenture.fsadd.extif.redmine.DefaultRedmineDataMapper;
import com.accenture.fsadd.extif.sonarqube.DefaultSonarQubeDataExtrator;
import com.accenture.fsadd.extif.sonarqube.DefaultSonarQubeDataMapper;

@Configuration
public class ExfIfConfig {

	@Bean
	public DefaultSonarQubeDataExtrator getDefaultSonarQubeDataExtrator() {
		return new DefaultSonarQubeDataExtrator();
	}

	@Bean
	public DefaultSonarQubeDataMapper getDefaultSonarQubeDataMapper() {
		return new DefaultSonarQubeDataMapper();
	}

	@Bean
	public DefaultRedmineDataExtrator getDefaultRedmineDataExtrator() {
		return new DefaultRedmineDataExtrator();
	}

	@Bean
	public DefaultRedmineDataMapper getDefaultRedmineDataMapper() {
		return new DefaultRedmineDataMapper();
	}

	@Bean
	public ExtIfRunner getExtIfRunner() {

		ExtIfRunner extIfRunner = new ExtIfRunner();
		extIfRunner.registerExtIf(ExtIfName.Redmine, this.getDefaultRedmineDataExtrator(),
				this.getDefaultRedmineDataMapper());
		extIfRunner.registerExtIf(ExtIfName.SonarQube, this.getDefaultSonarQubeDataExtrator(),
				this.getDefaultSonarQubeDataMapper());
		return extIfRunner;
	}
	//
	// @Bean
	// @Autowired
	// public ThreadPoolTaskScheduler getThreadPoolTaskScheduler(ExtIfRunner
	// runner){
	//
	// ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	// scheduler.initialize();
	// Date startTime =
	// Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
	// scheduler.scheduleAtFixedRate(runner, startTime, 10*60*1000);
	// return scheduler;
	//
	// }

}
