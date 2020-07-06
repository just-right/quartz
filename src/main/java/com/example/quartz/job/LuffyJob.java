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

        try {
            /**
             * 获取JobDetail和Trigger创建时的JobDataMap
             */
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

            JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();

            logger.info("执行定时任务！");
            jobDataMap.forEach((k, v) -> logger.info(k + ":" + v)
            );
            mergedJobDataMap.forEach((k, v) -> logger.info(k + ":" + v)
            );
        } catch (Exception e) {
            JobExecutionException exception = new JobExecutionException(e);
            /**
             * 方式一：工作立即重新开始
             */
            exception.setRefireImmediately(true);

            /**
             * 方式二：自动取消与此作业关联的所有触发器计划
             */
            //exception.setUnscheduleAllTriggers(true);
            throw exception;
        }

    }
}


