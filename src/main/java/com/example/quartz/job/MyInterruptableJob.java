package com.example.quartz.job;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

/**
 * https://www.cnblogs.com/zhuwenjoyce/p/11186399.html
 */
public class MyInterruptableJob implements InterruptableJob {

    public MyInterruptableJob() {
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
