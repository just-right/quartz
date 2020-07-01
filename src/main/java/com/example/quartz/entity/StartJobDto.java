package com.example.quartz.entity;

public class StartJobDto {

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    public StartJobDto(String jobClassName, String jobGroupName, String cronExpression) {
        this.jobClassName = jobClassName;
        this.jobGroupName = jobGroupName;
        this.cronExpression = cronExpression;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
