package com.vaskov.coursework.messenger.domain.models;

import java.util.ArrayList;
import java.util.List;

public class DialogDetails {

    private List<Message> messages;
    private UserData userData;

    public DialogDetails(UserData userData) {
        this.userData = userData;
        messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public UserData getUserData() {
        return userData;
    }
}
