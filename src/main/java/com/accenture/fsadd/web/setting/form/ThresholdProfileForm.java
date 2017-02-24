package com.accenture.fsadd.web.setting.form;

public class ThresholdProfileForm {
	private String id;
	private String project;
	private String system;
	private String catalog;
	private String kpi;
	private String overCast;
	private String rain;
	private String noticeMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

	public String getOverCast() {
		return overCast;
	}

	public void setOverCast(String overCast) {
		this.overCast = overCast;
	}

	public String getRain() {
		return rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public String getNoticeMsg() {
		return noticeMsg;
	}

	public void setNoticeMsg(String noticeMsg) {
		this.noticeMsg = noticeMsg;
	}
}
