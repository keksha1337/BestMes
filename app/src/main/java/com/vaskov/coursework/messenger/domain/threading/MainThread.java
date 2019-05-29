package com.vaskov.coursework.messenger.domain.threading;

public interface MainThread {
    void post(final Runnable runnable);
}
