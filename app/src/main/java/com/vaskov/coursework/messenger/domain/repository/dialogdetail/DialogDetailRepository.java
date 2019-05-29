package com.vaskov.coursework.messenger.domain.repository.dialogdetail;

import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;

public interface DialogDetailRepository {

    DialogDetails getDialogDetail(Dialog dialog);

    void saveDialogDetail(DialogDetails dialogDetails);
}
