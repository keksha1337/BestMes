package com.vaskov.coursework.messenger.domain.repository.userprofile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaskov.coursework.messenger.domain.models.UserProfile;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HardcodedUserProfileRepositoryImpl implements UserProfileRepository {
    @Override
    public UserProfile getUserProfile(String userSecretName) {
        try {
            List<UserProfile> userProfiles = new ArrayList<>();
            userProfiles.add(new UserProfile("user_1", "user_1", "1337"));
            userProfiles.add(new UserProfile("user_2", "user_2", "1488"));

            File file = new File(BaseApplication.getContext().getFilesDir() + "/userprofile.txt");
            file.createNewFile();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            BufferedWriter writer = new BufferedWriter(new FileWriter(BaseApplication.getContext().getFilesDir() + "/userprofile.txt"));

            writer.write(gson.toJson(userProfiles));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UserProfile(userSecretName, "user_1", "qwerty");
    }
}
