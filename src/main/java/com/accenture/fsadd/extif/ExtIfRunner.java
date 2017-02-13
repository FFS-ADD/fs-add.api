package com.accenture.fsadd.extif;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.fsadd.extif.sonarqube.SonarqubeDataMapping;

import com.accenture.fsadd.extif.sonarqube.SonarqubeDataExtrator;

public class ExtIfRunner implements Runnable {
	
	private static Logger log = Logger.getLogger(ExtIfRunner.class);
	
	@Autowired 
	private SonarqubeDataExtrator sonarqubeDataExtrator;
	
	@Autowired
	private SonarqubeDataMapping sonarqubeDataMapping;
	
	public void run() {
		log.info("Start");
		//sonarqubeDataExtrator.extractIssues();
		sonarqubeDataMapping.mappingDataToSonarDashboard();
		log.info("End");
		
	}
	public static void main(String args){
		new SonarqubeDataExtrator().extractIssues();
		
	}
}
