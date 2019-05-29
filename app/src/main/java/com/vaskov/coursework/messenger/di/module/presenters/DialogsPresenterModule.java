package com.vaskov.coursework.messenger.di.module.presenters;

import com.vaskov.coursework.messenger.domain.interactors.dialogs.DialogInteractor;
import com.vaskov.coursework.messenger.presenters.dialogs.DialogsPresenter;
import com.vaskov.coursework.messenger.presenters.dialogs.DialogsPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogsPresenterModule {
    @Provides
    DialogsPresenter provideHistoryPresenter(DialogsPresenter.View view,
                                             DialogInteractor dialogInteractor) {
        return new DialogsPresenterImpl(view, dialogInteractor);
    }
}
