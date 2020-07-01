package com.example.quartz.controller;

import com.example.quartz.entity.StartJobDto;
import com.example.quartz.job.MyJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.w3cschool.cn/quartz_doc/
 *
 * https://www.cnblogs.com/loong-hon/p/10912741.html
 *
 * https://www.jianshu.com/p/ce4c4400eea2
 *
 * https://github.com/tjfy1992/SpringBootQuartz
 *
 * https://cron.qqe2.com/
 *
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Qualifier("myScheduler")
    @Autowired
    private Scheduler scheduler;

    @PostMapping(value = "/startJob")
    public String startJob(@RequestBody StartJobDto dto) {
        String resInfo = null;
        Map<String,String> infoMap = new HashMap<>();
        infoMap.put("creator","luffy");
        JobDataMap dataMap = new JobDataMap(infoMap);
        try {
            //开启调度器
            scheduler.start();
            //创建JobDetail
            JobDetail jobDetail =  JobBuilder.newJob(getClass(dto.getJobClassName()).getClass())
                    .withIdentity(dto.getJobClassName(), dto.getJobGroupName())
                    .requestRecovery(true)
                    .setJobData(dataMap)
                    .build();
            //创建CronScheduleBuilder
            CronScheduleBuilder scheduleBuilder =  CronScheduleBuilder.cronSchedule(dto.getCronExpression());

            //创建触发器
            CronTrigger trigger =  TriggerBuilder.newTrigger()
                    .withIdentity(dto.getJobClassName(), dto.getJobGroupName())
                    .withSchedule(scheduleBuilder)
                    .build();
            //调度器关联Job和Trigger
            scheduler.scheduleJob(jobDetail,trigger);
            resInfo = "success";
        } catch (Exception e) {
            resInfo = "error";
        }
        return resInfo;
    }


    public static MyJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (MyJob) class1.newInstance();
    }

    /***
     * 1.1 ### 中断Job
     * scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
     * 1.2 ### 恢复Job
     * scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
     * 1.3 ### 删除Job
     * scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
     * scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
     * scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
     * 1.4 ### 更新Job
     * TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
     * // 表达式调度构建器
     * CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
     * CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
     * // 按新的cronExpression表达式重新构建trigger
     * trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
     * // 按新的trigger重新设置job执行
     * scheduler.rescheduleJob(triggerKey, trigger);
     */


}
