package com.retail.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

@ActiveProfiles("test")
@Configuration
public class FongoBaseConfig extends AbstractMongoConfiguration {

	@Autowired
	Environment env;
	
	@Override
	protected String getDatabaseName(){
		return env.getRequiredProperty("spring.data.mongodb.database");
	}
	
	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new Fongo(getDatabaseName()).getMongo();
	}
}
