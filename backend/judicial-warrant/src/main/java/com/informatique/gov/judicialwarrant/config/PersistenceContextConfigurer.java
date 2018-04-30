package com.informatique.gov.judicialwarrant.config;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.informatique.gov.judicialwarrant.domain.CreateLog;
import com.informatique.gov.judicialwarrant.domain.CreationAuditable;
import com.informatique.gov.judicialwarrant.domain.UpdateLog;
import com.informatique.gov.judicialwarrant.service.SecurityService;

@Configuration
@EnableJpaRepositories(basePackages = { "com.informatique.gov.judicialwarrant.persistence.repository" })
@EnableTransactionManagement
public class PersistenceContextConfigurer {
	
	private static final String[] ENTITY_PACKAGES = { "com.informatique.gov.judicialwarrant.domain" };
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment environment) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
		

		Properties jpaProperties = new Properties();

		jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
		jpaProperties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("spring.jpa.properties.hibernate.jdbc.batch-size"));
		jpaProperties.put("hibernate.session_factory.interceptor", hibernateInterceptor());

		

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
	
	@Bean
	public EmptyInterceptor hibernateInterceptor() {
		
	    return new EmptyInterceptor() {
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 5378984572498513074L;
			
			@Autowired
			private SecurityService securityService;

			@Override
	    	public boolean onSave(
	    			Object entity, 
	    			Serializable id, 
	    			Object[] state, 
	    			String[] propertyNames, 
	    			Type[] types) {
	    		try {
	    			if(entity instanceof CreationAuditable) {
	    				for(int i = 0; i < propertyNames.length; i++) {
	    					if("createLog".equals(propertyNames[i])) {
	    						state[i] = new CreateLog(securityService.getPrincipal(), new Date());
	    						return true;
	    					}
	    				}
	    			}
	    		}catch(Exception e) {
	    			return false;
	    		}
	    		
	    		return false;
	    	}
			
			@Override
			public boolean onFlushDirty(
					Object entity, 
					Serializable id, 
					Object[] currentState, 
					Object[] previousState, 
					String[] propertyNames, 
					Type[] types) {
				
				try {
	    			if(entity instanceof CreationAuditable) {
	    				for(int i = 0; i < propertyNames.length; i++) {
	    					if("updateLog".equals(propertyNames[i])) {
	    						currentState[i] = new UpdateLog(securityService.getPrincipal(), new Date());
	    						return true;
	    					}
	    				}
	    			}
	    		}catch(Exception e) {
	    			return false;
	    		}
				
				return false;
			}
	    };
	}

}
