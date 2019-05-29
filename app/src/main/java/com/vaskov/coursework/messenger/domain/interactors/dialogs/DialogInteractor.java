package com.vaskov.coursework.messenger.domain.interactors.dialogs;

import com.vaskov.coursework.messenger.domain.models.Dialog;

import java.util.List;

public interface DialogInteractor {

    void setCallback(Callback callback);

    void obtainDialogs();

    interface Callback {
        void onDialogsReceived(List<Dialog> dialogList);
    }
}
