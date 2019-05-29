package com.vaskov.coursework.messenger.domain.interactors.base;

import com.vaskov.coursework.messenger.domain.executor.Executor;
import com.vaskov.coursework.messenger.domain.threading.MainThread;

public class AbstractInteractor {
    protected Executor threadExecutor;
    protected MainThread mainThread;

    public AbstractInteractor(Executor executor, MainThread mainThread) {
        threadExecutor = executor;
        this.mainThread = mainThread;
    }
}
