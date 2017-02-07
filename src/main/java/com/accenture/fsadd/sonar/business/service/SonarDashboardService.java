/**
 * 
 */
package com.accenture.fsadd.sonar.business.service;

import com.accenture.fsadd.sonar.business.entity.SonarDashboardEntity;

/**
 * @author tianjian
 *
 */
public interface SonarDashboardService {
	
	public SonarDashboardEntity getSonarDashboard(String projectKey);
}
