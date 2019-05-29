package com.vaskov.coursework.messenger.di.component;

import com.vaskov.coursework.messenger.di.component.presenters.DialogsPresenterComponent;
import com.vaskov.coursework.messenger.di.module.ApplicationModule;
import com.vaskov.coursework.messenger.di.module.repository.DialogRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DialogRepositoryModule.class})
public interface AppComponent {
    DialogsPresenterComponent.Builder getDialogsPresenterBuilder();
}
