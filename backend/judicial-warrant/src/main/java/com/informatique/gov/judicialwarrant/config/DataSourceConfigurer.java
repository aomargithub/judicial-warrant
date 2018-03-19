package com.informatique.gov.judicialwarrant.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;


@Configuration
public class DataSourceConfigurer {
	
	@Autowired
	private Environment environment;
	
	@Bean(name = "dataSource")
	@Profile({ "test", "staging", "prod"})
	public DataSource dataSourceLookup() throws Exception {
	    JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
	    return dataSourceLookup.getDataSource(environment.getRequiredProperty("app.datasource.name"));
	}
	
	@Bean(name = "dataSource")
	@Profile({ "dev"})
	@ConfigurationProperties("app.datasource")
	public org.apache.tomcat.jdbc.pool.DataSource dataSourceCreator() {
	    return (org.apache.tomcat.jdbc.pool.DataSource)DataSourceBuilder.create().type(org.apache.tomcat.jdbc.pool.DataSource.class).build();
	}

}
