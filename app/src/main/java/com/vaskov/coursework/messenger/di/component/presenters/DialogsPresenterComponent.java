package com.vaskov.coursework.messenger.di.component.presenters;

import com.vaskov.coursework.messenger.di.module.interactors.DialogsInteractorModule;
import com.vaskov.coursework.messenger.di.module.presenters.DialogsPresenterModule;
import com.vaskov.coursework.messenger.presenters.dialogs.DialogsPresenter;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {DialogsInteractorModule.class, DialogsPresenterModule.class})
public interface DialogsPresenterComponent {
    DialogsPresenter getPresenter();

    @Subcomponent.Builder
    interface Builder {
        DialogsPresenterComponent build();

        @BindsInstance
        Builder setView(DialogsPresenter.View view);
    }
}
