package com.tecnotree.mongo.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


@Configuration
public class MongoConfig {

	private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

	@Value("${spring.data.mongodb.max-connections-per-host}")
	String maxConnectionsPerHost;

	@Value("${spring.data.mongodb.min-connections-per-host}")
	String minConnectionsPerHost;

	@Value("${spring.data.mongodb.server-selection-timeout}")
	String serverSelectionTimeout;

	@Value("${spring.data.mongodb.max-connection-idel-time}")
	String maxConnectionIdelTime;

	@Value("${spring.data.mongodb.max-connection-life-time}")
	String maxConnectionLifeTime;

	@Value("${spring.data.mongodb.connect-timeout}")
	String connectTimeout;

	@Value("${spring.data.mongodb.socket-timeout}")
	String socketTimeout;


	@Value("${spring.data.mongodb.username}")
	String username;

	@Value("${spring.data.mongodb.password}")
	String password;

	@Value("${spring.data.mongodb.database}")
	String dbName;

	@Value("${spring.data.mongodb.host}")
	String hostname;

	@Value("${spring.data.mongodb.port}")
	String port;

	@Bean
	@Autowired
	public MongoDbFactory mongoDbFactory() {
		
		MongoDbFactory mongoDbFactory = null;
		
		try {
		List<MongoCredential> creds = new ArrayList<MongoCredential>();
		creds.add(MongoCredential.createCredential(username, dbName, password.toCharArray()));

		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		builder.connectionsPerHost(Integer.parseInt(maxConnectionsPerHost));
		builder.minConnectionsPerHost(Integer.parseInt(minConnectionsPerHost));
		builder.serverSelectionTimeout(Integer.parseInt(serverSelectionTimeout));
		builder.socketTimeout(Integer.parseInt(socketTimeout));
		builder.connectTimeout(Integer.parseInt(connectTimeout));
		builder.maxConnectionLifeTime(Integer.parseInt(maxConnectionLifeTime));
		builder.maxConnectionIdleTime(Integer.parseInt(maxConnectionIdelTime));
		MongoClientOptions mongoClientOptions = builder.build();

		MongoClient mongoClient = new MongoClient(new ServerAddress(hostname, Integer.parseInt(port)), creds,
				mongoClientOptions);

		mongoDbFactory = new SimpleMongoDbFactory(mongoClient, dbName);
		} catch(Exception e) {
			
			
			logger.error("Error while connecting the database");
		}
		return mongoDbFactory;

	}

}
