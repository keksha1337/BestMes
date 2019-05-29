package com.vaskov.coursework.messenger.domain.interactors.dialogdetail;

import com.vaskov.coursework.messenger.domain.executor.Executor;
import com.vaskov.coursework.messenger.domain.interactors.base.AbstractInteractor;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;
import com.vaskov.coursework.messenger.domain.models.Message;
import com.vaskov.coursework.messenger.domain.repository.dialogdetail.DialogDetailRepository;
import com.vaskov.coursework.messenger.domain.threading.MainThread;

public class DialogDetailInteractorImpl extends AbstractInteractor implements DialogDetailInteractor {

    private Callback callback;
    private DialogDetailRepository dialogDetailRepository;
    private DialogDetails dialogDetails;

    public DialogDetailInteractorImpl(Executor executor, MainThread mainThread, DialogDetailRepository dialogDetailRepository) {
        super(executor, mainThread);
        this.dialogDetailRepository = dialogDetailRepository;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void obtainDialogDetail(Dialog dialog) {
        threadExecutor.execute(() -> {
            DialogDetails dialogDetails = dialogDetailRepository.getDialogDetail(dialog);
            this.dialogDetails = dialogDetails;
            mainThread.post(() -> callback.onDialogDetailReceived(dialogDetails));
        });
    }

    @Override
    public void sendMes(String mes) {
        threadExecutor.execute(() -> {
            dialogDetails.getMessages().add(new Message(mes, "00.00", dialogDetails.getMessages().get(0).getOwnersUserId()));
            dialogDetailRepository.saveDialogDetail(dialogDetails);
            mainThread.post(() -> callback.onSendMes());
        });
    }
}
