package com.enoteshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories(basePackages={"com.enoteshare"})
@SpringBootApplication(scanBasePackages={"com.enoteshare"})
public class NoteShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteShareApplication.class, args);
	}
}
