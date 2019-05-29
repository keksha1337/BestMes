package com.vaskov.coursework.messenger.domain.repository.userdata;

import com.vaskov.coursework.messenger.domain.models.UserData;

public interface UserDataRepository {

    UserData getUserData(String userId);
}
