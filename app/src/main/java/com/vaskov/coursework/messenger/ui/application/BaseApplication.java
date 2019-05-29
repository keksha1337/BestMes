package com.vaskov.coursework.messenger.ui.application;

import android.app.Application;
import android.content.Context;

import com.vaskov.coursework.messenger.di.component.AppComponent;
import com.vaskov.coursework.messenger.di.component.DaggerAppComponent;
import com.vaskov.coursework.messenger.di.module.ApplicationModule;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.domain.repository.userdata.HardcodedUserDataRepositoryImpl;

public class BaseApplication extends Application {

    private static volatile BaseApplication application;
    private static AppComponent component;
    private static UserData userData;
    private static Context context;

    public static AppComponent getComponent() {
        return component;
    }

    public static UserData getUserData() {
        return userData;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        context = getBaseContext();

        userData = new HardcodedUserDataRepositoryImpl().getUserData("user_1");

        initializeComponent();
    }

    public static Context getContext() {
        return context;
    }

    private void initializeComponent() {
        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }
}
