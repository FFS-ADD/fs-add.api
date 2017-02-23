package com.accenture.fsadd.web.setting.controller.model;

import java.math.BigInteger;

public class ProjectModel {
    private BigInteger id;
    private String projectName;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
