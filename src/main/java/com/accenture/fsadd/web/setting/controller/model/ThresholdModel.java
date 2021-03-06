package com.accenture.fsadd.web.setting.controller.model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class ThresholdModel {
    @Id
	private String id;
	private BigInteger projectNo;
	private String project;
	private String system;
	private String catalog;
	private String kpi;
	private double overCast;
	private double rain;
	private String noticeMsg;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigInteger getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(BigInteger projectNo) {
		this.projectNo = projectNo;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public double getOverCast() {
		return overCast;
	}
	public void setOverCast(double overCast) {
		this.overCast = overCast;
	}
	public double getRain() {
		return rain;
	}
	public void setRain(double rain) {
		this.rain = rain;
	}
	public String getNoticeMsg() {
		return noticeMsg;
	}
	public void setNoticeMsg(String noticeMsg) {
		this.noticeMsg = noticeMsg;
	}
}
