package com.vaskov.coursework.messenger.domain.repository.userprofile;

import com.vaskov.coursework.messenger.domain.models.UserProfile;

public interface UserProfileRepository {

    UserProfile getUserProfile(String userSecretName);
}
