package com.SpringMongo.WebConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	
	

	 @Bean
     public Docket api() {
         List<Parameter> newArrayList = new ArrayList<>(Arrays.asList((Parameter) new ParameterBuilder()
             .name("X-Auth-Token").description("Request validation Token")
             .modelRef(new ModelRef("String")).parameterType("header").required(false).build()));

         return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
             .paths(PathSelectors.any()).build().globalOperationParameters(newArrayList).enable(true);
     }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
