package com.vaskov.coursework.messenger.di.module.repository;

import com.vaskov.coursework.messenger.domain.repository.dialog.DialogRepository;
import com.vaskov.coursework.messenger.domain.repository.dialog.HardcodedDialogRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogRepositoryModule {
    @Provides
    public DialogRepository provideDialogsRepository() {
        return new HardcodedDialogRepositoryImpl();
    }
}
