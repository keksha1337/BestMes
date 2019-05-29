package com.vaskov.coursework.messenger.domain.models;

public class Dialog {

    private String username;
    private String userPhotoUri;
    private String lastMessage;
    private String timeOfLastMessage;
    private String id;
    private String owner;

    public Dialog(String id, String owner, String username, String userPhotoUri, String lastMessage, String timeOfLastMessage) {
        this.username = username;
        this.userPhotoUri = userPhotoUri;
        this.lastMessage = lastMessage;
        this.timeOfLastMessage = timeOfLastMessage;
        this.id = id;
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPhotoUri() {
        return userPhotoUri;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTimeOfLastMessage() {
        return timeOfLastMessage;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

}
