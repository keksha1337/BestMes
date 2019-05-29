package com.vaskov.coursework.messenger.presenters.dialogdetail;

import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;
import com.vaskov.coursework.messenger.domain.routers.dialogdetail.DialogDetailRouter;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.base.BaseView;

public interface DialogDetailPresenter extends BasePresenter {
    void setRouter(DialogDetailRouter dialogDetailRouter);
    void pressedSendMes(String mes);
    void setDialog(Dialog dialog);
    void pressedUserData();

    interface View extends BaseView {
        void showDialogDetail(DialogDetails dialogDetails);
        void onSendMes();
    }
}
