package com.example.quartz.job;


import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuffyJob implements MyJob{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        /**
         * 获取JobDetail和Trigger创建时的JobDataMap
         */
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        logger.info("执行定时任务！");

    }
}


