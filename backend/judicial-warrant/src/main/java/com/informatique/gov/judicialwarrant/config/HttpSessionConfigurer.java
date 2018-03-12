package com.informatique.gov.judicialwarrant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 120)
public class HttpSessionConfigurer {


	@Bean
	public HeaderHttpSessionStrategy sessionStrategy() {
		HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
		return headerHttpSessionStrategy;
	}
}
