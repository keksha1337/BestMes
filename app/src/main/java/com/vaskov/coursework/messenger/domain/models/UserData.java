package com.vaskov.coursework.messenger.domain.models;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private String username;
    private String userId;
    private String phoneNumber;
    private String imageUri;
    private String lastSince;
    private List<String> dialogId;

    public UserData(String username, String userId, String phoneNumber, String imageUri, String lastSince) {
        this.username = username;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.imageUri = imageUri;
        this.lastSince = lastSince;
        dialogId = new ArrayList<>();

    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getLastSeen() {
        return lastSince;
    }

    public List<String> getDialogId() {
        return dialogId;
    }
}
