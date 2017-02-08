/**
 * 
 */
package com.accenture.fsadd.sonar.business.service;

import java.util.List;

import com.accenture.fsadd.sonar.business.entity.Sonardashboard;

/**
 * @author tianjian
 *
 */
public interface SonarDashboardService {
	public Sonardashboard getSonarDashboard(String projectKey);
	
	public List<Sonardashboard> getSonarDashboardHist(String projectKey);
}
