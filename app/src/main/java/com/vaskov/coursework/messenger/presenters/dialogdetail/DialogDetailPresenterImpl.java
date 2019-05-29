package com.vaskov.coursework.messenger.presenters.dialogdetail;

import com.vaskov.coursework.messenger.domain.interactors.dialogdetail.DialogDetailInteractor;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;
import com.vaskov.coursework.messenger.domain.routers.dialogdetail.DialogDetailRouter;
import com.vaskov.coursework.messenger.presenters.base.AbstractPresenter;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class DialogDetailPresenterImpl extends AbstractPresenter<DialogDetailPresenter.View> implements DialogDetailPresenter, DialogDetailInteractor.Callback {

    private DialogDetailInteractor dialogDetailInteractor;
    private DialogDetailRouter dialogDetailRouter;
    private Dialog dialog;
    private DialogDetails dialogDetails;

    public DialogDetailPresenterImpl(DialogDetailPresenter.View view, DialogDetailInteractor dialogDetailInteractor) {
        super(view);
        this.dialogDetailInteractor = dialogDetailInteractor;
        this.dialogDetailInteractor.setCallback(this);
    }

    @Override
    public void setRouter(DialogDetailRouter dialogDetailRouter) {
        this.dialogDetailRouter = dialogDetailRouter;
    }

    @Override
    public void resume() {
        dialogDetailInteractor.obtainDialogDetail(dialog);
    }

    @Override
    public void pressedSendMes(String mes) {
        dialogDetailInteractor.sendMes(mes);
    }

    @Override
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void pressedUserData() {
        dialogDetailRouter.showUserData(dialogDetails.getUserData());
    }

    @Override
    public void onDialogDetailReceived(DialogDetails dialogDetails) {
        this.dialogDetails = dialogDetails;
        view.showDialogDetail(dialogDetails);
    }

    @Override
    public void onSendMes() {
        view.onSendMes();
    }
}
