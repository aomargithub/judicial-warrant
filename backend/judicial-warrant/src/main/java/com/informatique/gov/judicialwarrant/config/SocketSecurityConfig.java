package com.informatique.gov.judicialwarrant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import com.informatique.gov.judicialwarrant.support.dataenum.UserRoleEnum;

@Configuration
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages.simpDestMatchers("/officer-topic/**").authenticated().anyMessage().hasAnyRole(UserRoleEnum.OFFICER.getCode());
		messages.simpDestMatchers("/user-topic/**").authenticated().anyMessage().hasAnyRole(UserRoleEnum.USER.getCode());
		messages.simpDestMatchers("/trainer-topic/**").authenticated().anyMessage().hasAnyRole(UserRoleEnum.TRAINING_INSTITUTE.getCode());
	}
}