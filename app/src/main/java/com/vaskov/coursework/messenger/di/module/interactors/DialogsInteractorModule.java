package com.vaskov.coursework.messenger.di.module.interactors;

import com.vaskov.coursework.messenger.di.module.repository.UserDataModule;
import com.vaskov.coursework.messenger.domain.executor.Executor;
import com.vaskov.coursework.messenger.domain.interactors.dialogs.DialogInteractor;
import com.vaskov.coursework.messenger.domain.interactors.dialogs.DialogInteractorImpl;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.domain.repository.dialog.DialogRepository;
import com.vaskov.coursework.messenger.domain.threading.MainThread;

import dagger.Module;
import dagger.Provides;

@Module(includes = {UserDataModule.class})
public class DialogsInteractorModule {
    @Provides
    public DialogInteractor provideDialogsInteractor(Executor executor,
                                                     MainThread mainThread,
                                                     UserData userData,
                                                     DialogRepository dialogRepository) {
        return new DialogInteractorImpl(executor, mainThread, userData, dialogRepository);
    }
}
