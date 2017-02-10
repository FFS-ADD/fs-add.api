package com.accenture.fsadd.sonar.controller.model;

import java.util.List;

import com.accenture.fsadd.common.mvc.model.CommonModel;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;

public class HistModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Sonardashboard> sonardashboardlList;

	public List<Sonardashboard> getSonardashboardlList() {
		return sonardashboardlList;
	}

	public void setSonardashboardlList(List<Sonardashboard> sonardashboardlList) {
		this.sonardashboardlList = sonardashboardlList;
	}


}
