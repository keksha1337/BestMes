package com.vaskov.coursework.messenger.domain.repository.userdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HardcodedUserDataRepositoryImpl implements UserDataRepository {
    @Override
    public UserData getUserData(String userId) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(BaseApplication.getContext().getFilesDir() + "/userdata.txt"));

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<ArrayList<UserData>>() {}.getType();
            List<UserData> userDataList = gson.fromJson(reader.readLine(), listType);

            for (UserData userData : userDataList) {
                if (userData.getUserId().equals(userId)) {
                    return userData;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
