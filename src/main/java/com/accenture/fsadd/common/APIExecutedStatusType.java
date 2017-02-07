package com.accenture.fsadd.common;

public enum APIExecutedStatusType {

    SUCCESS(1, "Success"),
    ERROR(0, "Error");

    private int value;
    private String description;

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    private APIExecutedStatusType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}