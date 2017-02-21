package com.accenture.fsadd.extif;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus.Status;
import com.accenture.fsadd.extif.service.ExtIfService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtIfRunnerTest {

	private static Logger log = LoggerFactory.getLogger(ExtIfRunner.class);
	
	@Autowired
	ApplicationContext applicationCntenxt;

	@Autowired
	private ExtIfService extIfService;
	
	
	class SuccessExtrator implements ExtIfDataExtrator{

		@Override
		public void extrator(LocalDateTime lastExecutedTime) {
			
			log.info("lastExecutedTime={}", lastExecutedTime);
			
		}	
	}
	
	class ErrorExtrator implements ExtIfDataExtrator{

		@Override
		public void extrator(LocalDateTime lastExecutedTime) {
			throw new RuntimeException("ErrorExtrator");
			
		}
		
	}
	
	class SuccessMapper implements ExtIfDataMapper{

		@Override
		public void map(LocalDateTime lastExecutedTime) {
			log.info("lastExecutedTime={}", lastExecutedTime);		
		}
		
	}
	
	
	
	class ErrorMapper implements ExtIfDataMapper{

		@Override
		public void map(LocalDateTime lastExecutedTime) {
			throw new RuntimeException("ErrorMapper");			
		}
		
	}
	
	@Test
	public void test1Success() throws InterruptedException {
		

		ExtIfRunner runner = new ExtIfRunner();

		runner.setExtIfService(extIfService);
		LocalDateTime now = LocalDateTime.now();
		runner.registerExtIf(ExtIfName.Redmine, new SuccessExtrator(), new SuccessMapper());
		// Start
		runner.start();
		Thread.sleep(15000L);
		ExtIfRunnerStatus status = extIfService.queryExtIfStatus(ExtIfName.Redmine);
		assertThat(Status.COMPLETED == status.getStatus());
		assertThat(status.getLastExecutedOn().isAfter(now.plusSeconds(10)));		
		
	}
	
	@Test
	public void test2Error1() throws InterruptedException {
		

		ExtIfRunner runner = new ExtIfRunner();
		runner.setExtIfService(extIfService);
		LocalDateTime lastExecutedOn = extIfService.queryExtIfStatus(ExtIfName.Redmine).getLastExecutedOn();
		runner.registerExtIf(ExtIfName.Redmine, new ErrorExtrator(), new SuccessMapper());
		// Start
		runner.start();
		Thread.sleep(15000L);
		ExtIfRunnerStatus status = extIfService.queryExtIfStatus(ExtIfName.Redmine);
		assertThat(Status.ERROR == status.getStatus());
		assertThat(status.getLastExecutedOn().equals(lastExecutedOn));		
		
	}
	
	@Test
	public void test3Error2() throws InterruptedException {
		

		ExtIfRunner runner = new ExtIfRunner();
		runner.setExtIfService(extIfService);
		LocalDateTime lastExecutedOn = extIfService.queryExtIfStatus(ExtIfName.Redmine).getLastExecutedOn();
		runner.registerExtIf(ExtIfName.Redmine, new SuccessExtrator(), new ErrorMapper());
		// Start
		runner.start();
		Thread.sleep(15000L);
		ExtIfRunnerStatus status = extIfService.queryExtIfStatus(ExtIfName.Redmine);
		assertThat(Status.ERROR == status.getStatus());
		assertThat(status.getLastExecutedOn().equals(lastExecutedOn));		
		
	}

}
