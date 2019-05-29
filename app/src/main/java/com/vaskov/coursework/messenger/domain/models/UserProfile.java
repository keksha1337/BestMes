package com.vaskov.coursework.messenger.domain.models;

public class UserProfile {

    private String userSecretName;
    private String userId;
    private String UserPassword;

    public UserProfile(String userSecretName, String userId, String userPassword) {
        this.userSecretName = userSecretName;
        this.userId = userId;
        UserPassword = userPassword;
    }

    public String getUserSecretName() {
        return userSecretName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return UserPassword;
    }
}
