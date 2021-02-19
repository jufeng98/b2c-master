package com.javamaster.b2c.cloud.test.scheduled.config;

import com.javamaster.b2c.cloud.test.scheduled.dbtask.ScheduledTask;
import com.javamaster.b2c.cloud.test.scheduled.entity.SpringSchuduledCron;
import com.javamaster.b2c.cloud.test.scheduled.respsitory.SpringSchuduledCronRepository;
import com.javamaster.b2c.cloud.test.scheduled.task.DynamicPrintTask;
import com.javamaster.b2c.cloud.test.scheduled.task.DynamicPrintTask1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author yudong
 * @date 2019/5/10
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CronProperties cronProperties;

    @Autowired
    private SchuduledCronProperties schuduledCronProperties;

    @Autowired
    private SpringSchuduledCronRepository cronRepository;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 通过数据库动态改变执行周期
        taskRegistrar.addTriggerTask(new DynamicPrintTask(),
                triggerContext -> {
                    String cron = cronRepository.findByCronId(1).getCronExpression();
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );

        // 通过配置文件信息动态改变执行周期
        taskRegistrar.addTriggerTask(new DynamicPrintTask1(),
                triggerContext -> new CronTrigger(cronProperties.getCron()).nextExecutionTime(triggerContext)
        );

        for (SpringSchuduledCron springSchuduledCron : schuduledCronProperties.getCronList()) {
            Class<?> clazz;
            Object task;
            try {
                clazz = Class.forName(springSchuduledCron.getCronKey());
                task = context.getBean(clazz);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("spring_scheduled_cron表数据" + springSchuduledCron.getCronKey() + "有误", e);
            } catch (BeansException e) {
                throw new IllegalArgumentException(springSchuduledCron.getCronKey() + "未纳入到spring管理", e);
            }
            Assert.isAssignable(ScheduledTask.class, task.getClass(), "必须实现ScheduledTask接口");
            taskRegistrar.addTriggerTask(((Runnable) task),
                    triggerContext -> new CronTrigger(springSchuduledCron.getCronExpression()).nextExecutionTime(triggerContext)
            );
        }

    }


    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }

}
