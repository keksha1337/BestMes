package com.vaskov.coursework.messenger.domain.repository.dialogdetail;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;
import com.vaskov.coursework.messenger.domain.repository.userdata.UserDataRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class HardcodedDialogDetailRepositoryImpl implements DialogDetailRepository {

    private UserDataRepository userDataRepository;
    private Context context;
    private String lastDialogId;

    public HardcodedDialogDetailRepositoryImpl(Context context, UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
        this.context = context;
    }

    @Override
    public DialogDetails getDialogDetail(Dialog dialog) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        lastDialogId = dialog.getId();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(context.getFilesDir() + "/dialog_" + dialog.getId() + ".txt"));
            return gson.fromJson(reader.readLine(), DialogDetails.class);
        } catch (Exception e) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(context.getFilesDir() + "/reposotory.txt"));
                return gson.fromJson(reader.readLine(), DialogDetails.class);
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void saveDialogDetail(DialogDetails dialogDetails) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(context.getFilesDir() + "/dialog_" + lastDialogId + ".txt"));
            writer.write("" + gson.toJson(dialogDetails));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
