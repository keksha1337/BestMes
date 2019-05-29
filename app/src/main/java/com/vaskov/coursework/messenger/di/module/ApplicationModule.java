package com.vaskov.coursework.messenger.di.module;

import android.content.Context;

import com.vaskov.coursework.messenger.domain.executor.Executor;
import com.vaskov.coursework.messenger.domain.executor.ThreadExecutor;
import com.vaskov.coursework.messenger.domain.threading.MainThread;
import com.vaskov.coursework.messenger.domain.threading.MainThreadImpl;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    MainThread provideMainThread() {
        return MainThreadImpl.getInstance();
    }

    @Provides
    Executor provideThreadExecutor() {
        return ThreadExecutor.getInstance();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}
