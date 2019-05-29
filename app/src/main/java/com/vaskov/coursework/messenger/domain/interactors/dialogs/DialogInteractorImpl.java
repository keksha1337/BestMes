package com.vaskov.coursework.messenger.domain.interactors.dialogs;

import android.os.Build;

import com.vaskov.coursework.messenger.domain.executor.Executor;
import com.vaskov.coursework.messenger.domain.interactors.base.AbstractInteractor;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.domain.repository.dialog.DialogRepository;
import com.vaskov.coursework.messenger.domain.threading.MainThread;

import java.util.List;

public class DialogInteractorImpl extends AbstractInteractor implements DialogInteractor {

    private Callback callback;
    private DialogRepository dialogRepository;
    private UserData userData;

    public DialogInteractorImpl(Executor executor, MainThread mainThread, UserData userData, DialogRepository dialogRepository) {
        super(executor, mainThread);
        this.dialogRepository = dialogRepository;
        this.userData = userData;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void obtainDialogs() {
        threadExecutor.execute(() -> {
            List<Dialog> dialogs = dialogRepository.getDialogs(userData.getDialogId());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dialogs.sort((o1, o2) -> (o2.getTimeOfLastMessage().compareTo(o1.getTimeOfLastMessage())));
            }
            mainThread.post(() -> callback.onDialogsReceived(dialogs));
        });
    }
}
