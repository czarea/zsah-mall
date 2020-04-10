package com.czarea.zsah.goods.config;

import com.czarea.zsah.goods.job.GoodsReduceFallBackJob;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * quartz配置类
 *
 * @author zhouzx
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    //@Bean
    public JobDetailFactoryBean goodsReduceFallBackJobDetail() {
        return createJobDetail(GoodsReduceFallBackJob.class);
    }

    //@Bean
    public CronTriggerFactoryBean goodsReduceFallBackJobTrigger(@Qualifier("goodsReduceFallBackJobDetail") JobDetail jobDetail,
        @Value("${quartz.goodsReduceJobCron}") String cronExpression) {
        return createCronTrigger(jobDetail, cronExpression);
    }

    private static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

    private static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        return factoryBean;
    }
}
