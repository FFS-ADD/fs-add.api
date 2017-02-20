package com.accenture.fsadd.extif.entity;

import org.springframework.data.annotation.Id;

public class ExtIfSetting {
	
	@Id
	private String id;
	
	private RedmineSetting redmineSetting;
	
	private SonarQubeSetting sonarQubeSetting;
	
	private ExtIfRunnerSetting extIfRunnerSetting;

	public RedmineSetting getRedmineSetting() {
		return redmineSetting;
	}

	public void setRedmineSetting(RedmineSetting redmineSetting) {
		this.redmineSetting = redmineSetting;
	}

	public SonarQubeSetting getSonarQubeSetting() {
		return sonarQubeSetting;
	}

	public void setSonarQubeSetting(SonarQubeSetting sonarQubeSetting) {
		this.sonarQubeSetting = sonarQubeSetting;
	}

	public ExtIfRunnerSetting getExtIfRunnerSetting() {
		return extIfRunnerSetting;
	}

	public void setExtIfRunnerSetting(ExtIfRunnerSetting extIfRunnerSetting) {
		this.extIfRunnerSetting = extIfRunnerSetting;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extIfRunnerSetting == null) ? 0 : extIfRunnerSetting.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((redmineSetting == null) ? 0 : redmineSetting.hashCode());
		result = prime * result + ((sonarQubeSetting == null) ? 0 : sonarQubeSetting.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtIfSetting other = (ExtIfSetting) obj;
		if (extIfRunnerSetting == null) {
			if (other.extIfRunnerSetting != null)
				return false;
		} else if (!extIfRunnerSetting.equals(other.extIfRunnerSetting))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (redmineSetting == null) {
			if (other.redmineSetting != null)
				return false;
		} else if (!redmineSetting.equals(other.redmineSetting))
			return false;
		if (sonarQubeSetting == null) {
			if (other.sonarQubeSetting != null)
				return false;
		} else if (!sonarQubeSetting.equals(other.sonarQubeSetting))
			return false;
		return true;
	}


}
