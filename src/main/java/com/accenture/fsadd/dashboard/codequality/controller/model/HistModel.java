package com.accenture.fsadd.dashboard.codequality.controller.model;

import java.util.List;

import com.accenture.fsadd.dashboard.codequality.business.entity.Sonardashboard;

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
