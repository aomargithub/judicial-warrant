package com.informatique.gov.judicialwarrant.support.websocket;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WebSocketSender {

    private SimpMessageSendingOperations messagingTemplate;
	
	public void sendToTopic(String topic, String message) throws Exception {
		messagingTemplate.convertAndSend(topic, message);
	}

	
}
