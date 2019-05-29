package com.vaskov.coursework.messenger.presenters.dialogs;

import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.routers.dialogs.DialogsRouter;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.base.BaseView;

import java.util.List;

public interface DialogsPresenter extends BasePresenter {

    void dialogSelected(Dialog dialog);
    void setRouter(DialogsRouter dialogsRouter);

    interface View extends BaseView {
        void showDialogsList(List<Dialog> dialogs);
    }
}
