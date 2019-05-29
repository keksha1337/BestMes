package com.vaskov.coursework.messenger.presenters;

import com.vaskov.coursework.messenger.presenters.dialogs.DialogsPresenter;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

public class PresentersFactory {
    public static DialogsPresenter getDialogsPresenter(DialogsPresenter.View view) {
        return BaseApplication.getComponent().getDialogsPresenterBuilder().setView(view).build().getPresenter();
    }
}
