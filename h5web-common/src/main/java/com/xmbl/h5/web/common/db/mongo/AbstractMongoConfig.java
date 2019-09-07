package com.xmbl.h5.web.common.db.mongo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import lombok.Data;
 
@Data
public abstract class AbstractMongoConfig {
	private String host, database, username, password;
	private int port;
 
	public MongoDbFactory mongoDbFactory() throws Exception {
		ServerAddress addr = new ServerAddress(host, port);
		MongoClientOptions options = MongoClientOptions.builder().connectTimeout(30000).build();
		MongoClient mongoClient = null;
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
			mongoClient = new MongoClient(addr, credential, options);
		}else {
			mongoClient = new MongoClient(addr, options);
		}
		
		SimpleMongoDbFactory factory = new SimpleMongoDbFactory(mongoClient, database);
		return factory;
	}
 
	abstract public MongoTemplate getMongoTemplate() throws Exception;
}
