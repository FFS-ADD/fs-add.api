/**
 * 
 */
package com.accenture.fsadd.dashboard.codequality.business.service;

import java.util.List;

import com.accenture.fsadd.dashboard.codequality.business.entity.Sonardashboard;

/**
 * @author tianjian
 *
 */
public interface SonarDashboardService {
	public Sonardashboard getSonarDashboard();
	
	public List<Sonardashboard> getSonarDashboardHist();
}
