package com.example.quartz.job;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution

public class LuffyJob implements MyJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        /**
         * 获取JobDetail和Trigger创建时的JobDataMap
         */
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();

        logger.info("执行定时任务！");
        jobDataMap.forEach((k, v) ->
                logger.info(k + ":" + v)
        );
        mergedJobDataMap.forEach((k, v) ->
                logger.info(k + ":" + v)
        );

    }
}


