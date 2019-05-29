package com.vaskov.coursework.messenger.domain.repository.dialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HardcodedDialogRepositoryImpl implements DialogRepository {
    @Override
    public List<Dialog> getDialogs(List<String> dialogId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(BaseApplication.getContext().getFilesDir() + "/dialogs.txt"));
            List<Dialog> result = new ArrayList<>();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Type listType = new TypeToken<ArrayList<Dialog>>(){}.getType();
            List<Dialog> dialogs = gson.fromJson(reader.readLine(), listType);

            for (Dialog dialog : dialogs) {
                if (dialogId.contains(dialog.getId())) {
                    result.add(dialog);
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
