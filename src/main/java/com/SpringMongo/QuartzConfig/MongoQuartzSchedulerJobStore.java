package com.SpringMongo.QuartzConfig;

import com.SpringMongo.CommonConstant.CommonConstants;
import com.SpringMongo.PropertyReader.PropertyReader;
import com.novemberain.quartz.mongodb.MongoDBJobStore;

public class MongoQuartzSchedulerJobStore extends MongoDBJobStore {


	private static String mongoUri;
	private static String mongoUser;
	private static String databaseName;
	private static String mongoPass;
	private static Integer Socket_Time_Out;
	private static Integer connection_Time_Out;

	public MongoQuartzSchedulerJobStore() {
		super();
		initializeMongo();
		setMongoUri("mongodb://" + mongoUri);
		setDbName(databaseName);
		setUsername(mongoUser);
		setPassword(mongoPass);
		setMongoOptionEnableSSL(true);
		setMongoOptionSslInvalidHostNameAllowed(true);
		setMongoOptionConnectTimeoutMillis(connection_Time_Out);
		setMongoOptionSocketTimeoutMillis(Socket_Time_Out);
	}

	private static void initializeMongo() {
		PropertyReader propertyReader = new PropertyReader(CommonConstants.QUERY_PROPERTIES_FILENAME, "");
		mongoUri = propertyReader.getProperty(CommonConstants.MONGODB_URI);
		databaseName = propertyReader.getProperty(CommonConstants.MONGODB_DB_NAME);
		mongoUser = propertyReader.getProperty(CommonConstants.MONGODB_USERNAME);
		mongoPass = propertyReader.getProperty(CommonConstants.MONGODB_AUTHENTICATION);
		connection_Time_Out = Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_CONNECTION_TIMEOUT));
		Socket_Time_Out = Integer.parseInt(propertyReader.getProperty(CommonConstants.MONGODB_SOCKET_TIMEOUT));
	}

}
