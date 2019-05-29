package com.vaskov.coursework.messenger.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.UserProfile;
import com.vaskov.coursework.messenger.domain.repository.userprofile.HardcodedUserProfileRepositoryImpl;
import com.vaskov.coursework.messenger.domain.repository.userprofile.UserProfileRepository;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthentificationActivity extends Activity {

    @BindView(R.id.authentification_login_et)
    EditText login;

    @BindView(R.id.authentification_password_et)
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authentification);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.continue_button_tv)
    public void continueWasPressed() {
        File authentificationFile = new File(BaseApplication.getContext().getFilesDir() + "authentification.txt");

        UserProfileRepository userProfileRepository = new HardcodedUserProfileRepositoryImpl();

        try {
            if (userProfileRepository.getUserProfile(login.getText().toString()).getUserPassword().equals(password.getText().toString())) {
                if (authentificationFile.exists()) {
                    authentificationFile.deleteOnExit();
                }
                authentificationFile.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(BaseApplication.getContext().getFilesDir() + "authentification.txt"));

                UserProfile userProfile = new UserProfile(login.getText().toString(), login.getText().toString(), password.getText().toString());

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                writer.write(gson.toJson(userProfile));
                writer.flush();
                writer.close();

                showMain();
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
