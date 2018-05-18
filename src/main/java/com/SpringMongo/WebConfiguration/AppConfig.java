package com.SpringMongo.WebConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.SpringMongo.CommonConstant.CommonConstants;
import com.SpringMongo.PropertyReader.PropertyReader;

import ch.rasc.sse.eventbus.config.EnableSseEventBus;

@EnableScheduling
@EnableSseEventBus
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.SpringMongo")
public class AppConfig extends WebMvcConfigurerAdapter {

	
	
	@Bean(name = CommonConstants.QUERY_PROPERTY_READER)
	public PropertyReader propertReader() {
		return new PropertyReader(CommonConstants.QUERY_PROPERTIES_FILENAME, "");
	}
	


}
