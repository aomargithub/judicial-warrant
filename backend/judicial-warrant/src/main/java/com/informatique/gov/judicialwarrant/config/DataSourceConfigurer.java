package com.informatique.gov.judicialwarrant.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;


@Configuration
public class DataSourceConfigurer {
	
	@Autowired
	private Environment environment;
	
	
	/*
	 * We added destroyMethod = "" intentionally as if destroyMethod is not specified, spring will try to find it,
	 * and apparently when that happens, the datasource to be closed and the JNDI key to be removed from weblogic JNDI tree.
	 * Changing it to "" forces it to not look for a destroyMethod.
	 * 
	 *https://stackoverflow.com/questions/19158837/weblogic-datasource-disappears-from-jndi-tree
	 *
	 */
	@Bean(name = "dataSource", destroyMethod = "")
	@Profile({ "test", "staging", "prod"})
	public DataSource dataSourceLookup() throws Exception {
	    JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
	    return dataSourceLookup.getDataSource(environment.getRequiredProperty("app.datasource.name"));
	}
}
