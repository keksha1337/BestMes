package com.vaskov.coursework.messenger.di.module.repository;

import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class UserDataModule {
    @Provides
    public UserData provideUserData() {
        return BaseApplication.getUserData();
    }
}
