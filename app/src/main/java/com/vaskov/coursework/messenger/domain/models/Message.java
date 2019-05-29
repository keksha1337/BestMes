package com.vaskov.coursework.messenger.domain.models;

public class Message {

    private String text;
    private String time;
    private String ownersUserId;

    public Message(String text, String time, String ownersUserId) {
        this.text = text;
        this.time = time;
        this.ownersUserId = ownersUserId;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getOwnersUserId() {
        return ownersUserId;
    }
}
