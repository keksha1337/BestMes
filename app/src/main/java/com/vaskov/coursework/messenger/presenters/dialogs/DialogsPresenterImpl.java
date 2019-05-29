package com.vaskov.coursework.messenger.presenters.dialogs;

import com.vaskov.coursework.messenger.domain.interactors.dialogs.DialogInteractor;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.routers.dialogs.DialogsRouter;
import com.vaskov.coursework.messenger.presenters.base.AbstractPresenter;

import java.util.List;

public class DialogsPresenterImpl extends AbstractPresenter<DialogsPresenter.View> implements DialogsPresenter, DialogInteractor.Callback {

    private DialogInteractor dialogInteractor;
    private DialogsRouter dialogsRouter;

    public DialogsPresenterImpl(View view, DialogInteractor dialogInteractor) {
        super(view);
        this.dialogInteractor = dialogInteractor;
        this.dialogInteractor.setCallback(this);
    }

    @Override
    public void resume() {
        dialogInteractor.obtainDialogs();
    }

    @Override
    public void onDialogsReceived(List<Dialog> dialogList) {
        view.showDialogsList(dialogList);
    }

    @Override
    public void dialogSelected(Dialog dialog) {
        dialogsRouter.showDialogDetail(dialog);
    }

    @Override
    public void setRouter(DialogsRouter dialogsRouter) {
        this.dialogsRouter = dialogsRouter;
    }
}
