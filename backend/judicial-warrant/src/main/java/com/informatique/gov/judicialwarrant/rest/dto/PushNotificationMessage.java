package com.informatique.gov.judicialwarrant.rest.dto;

public class PushNotificationMessage {

	private MessageType type;
    private String content;

    public enum MessageType {
        SUBMIT,
        ACCEPT,
        INPROGRESS
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
