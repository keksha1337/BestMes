package com.vaskov.coursework.messenger.domain.repository.dialog;

import com.vaskov.coursework.messenger.domain.models.Dialog;

import java.util.List;

public interface DialogRepository {

    List<Dialog> getDialogs(List<String> dialogId);
}
