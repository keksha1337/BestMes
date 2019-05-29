package com.vaskov.coursework.messenger.ui.base;

public interface BaseView {
    void showMessage(int resourceId);

    void showMessage(String message);

    void showProgress();

    void hideProgress();
}
