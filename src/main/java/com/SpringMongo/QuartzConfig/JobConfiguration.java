package com.SpringMongo.QuartzConfig;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.SpringMongo.CommonConstant.CommonConstants;
import com.SpringMongo.PropertyReader.PropertyReader;



@Configuration
public class JobConfiguration {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	@Qualifier(CommonConstants.QUERY_PROPERTY_READER)
	private PropertyReader propertyReader;
	
	private static String newCronExpression;

	@PostConstruct
	private void initialize() throws Exception {
		newCronExpression = propertyReader.getProperty(SchedulerConstants.JOB_EXPRESSION);
		schedulerFactoryBean.getScheduler().addJob(simpleJobDetail(), true, true);

		if (!schedulerFactoryBean.getScheduler()
				.checkExists(new TriggerKey(SchedulerConstants.SIMPLE_POLLING_TRIGGER_KEY,
						SchedulerConstants.SIMPLE_POLLING_GROUP))) {
			schedulerFactoryBean.getScheduler().scheduleJob(simpleJobDetail(), simpleJobTrigger());
		}
	}
	
	private static JobDetail simpleJobDetail() {
		JobDetailImpl leadNotificationJobDetail = new JobDetailImpl();

		leadNotificationJobDetail.setKey(new JobKey(SchedulerConstants.SIMPLE_POLLING_JOB_KEY,
				SchedulerConstants.SIMPLE_POLLING_GROUP));
		leadNotificationJobDetail.setJobClass(SimpleScheduler.class);
		leadNotificationJobDetail.setDurability(true);

		return leadNotificationJobDetail;
	}
	
	/*	private static Trigger leadNotificationJobTrigger() {
		return newTrigger().forJob(leadNotificationJobDetail())
				.withIdentity(SchedulerConstants.SIMPLE_POLLING_TRIGGER_KEY,
						SchedulerConstants.SIMPLE_POLLING_GROUP)
				.withPriority(50).withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(1))
				.startAt(Date.from(LocalDateTime.now().plusSeconds(3).atZone(ZoneId.systemDefault()).toInstant()))
				.build();
	}*/
	
	private static Trigger simpleJobTrigger() {
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(newCronExpression);
		CronTrigger crontrigger = TriggerBuilder.newTrigger()
				.withIdentity(SchedulerConstants.SIMPLE_POLLING_TRIGGER_KEY,
						SchedulerConstants.SIMPLE_POLLING_GROUP)
				.withSchedule(builder.withMisfireHandlingInstructionFireAndProceed()).build();
		return crontrigger;
	}

}
