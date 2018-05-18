package com.SpringMongo.QuartzConfig;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;



@PersistJobDataAfterExecution
@DisallowConcurrentExecution()
public class SimpleScheduler extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("*****SIMPLE JOBS SCHEDULER ON CLUSTERED ENV*****");
	}

}
