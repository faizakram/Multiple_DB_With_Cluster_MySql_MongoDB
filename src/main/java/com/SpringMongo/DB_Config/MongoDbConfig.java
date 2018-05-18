package com.SpringMongo.DB_Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.SpringMongo.CommonConstant.CommonConstants;
import com.SpringMongo.PropertyReader.PropertyReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoDbConfig {

	@Autowired
	@Qualifier(CommonConstants.QUERY_PROPERTY_READER)
	private PropertyReader propertyReader;

	public MongoClient getMongoClient() throws Exception {
		MongoCredential mongocre = MongoCredential.createCredential(
				propertyReader.getProperty(CommonConstants.MONGODB_USERNAME),
				propertyReader.getProperty(CommonConstants.MONGODB_DB_NAME),
				propertyReader.getProperty(CommonConstants.MONGODB_AUTHENTICATION).toCharArray());
		List<ServerAddress> seeds = new ArrayList<>();
		seeds.add(new ServerAddress(propertyReader.getProperty(CommonConstants.MONGODB_HOST),
				Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_PORT))));
		MongoClientOptions.Builder mongoOperations = MongoClientOptions.builder();
		mongoOperations.connectionsPerHost(
				Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_CONNECTION_PER_HOST)));
		mongoOperations.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_THREAD_ALLOW)));
		mongoOperations.connectTimeout(Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_CONNECTION_TIMEOUT)));
		mongoOperations.socketTimeout(Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_SOCKET_TIMEOUT)));
		mongoOperations.maxWaitTime(Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_MAX_WAIT_TIME)));
		MongoClientOptions options = mongoOperations.build();
		return new MongoClient(seeds, mongocre, options);
	}

	/**
	 * Template ready to use to operate on the database
	 * 
	 * @return Mongo Template ready to use
	 */
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(getMongoClient(), propertyReader.getProperty(CommonConstants.MONGODB_DB_NAME));
	}

}
