package com.vaskov.coursework.messenger.domain.interactors.dialogdetail;

import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;

public interface DialogDetailInteractor {
    void setCallback(Callback callback);
    void obtainDialogDetail(Dialog dialog);
    void sendMes(String mes);

    interface Callback {
        void onDialogDetailReceived(DialogDetails dialogDetails);
        void onSendMes();
    }
}
