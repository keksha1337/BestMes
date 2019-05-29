package com.vaskov.coursework.messenger.ui.main.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.UserProfile;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangeUsernameOrPasswordFragment extends BaseFragment {

    @BindView(R.id.old_password_et)
    EditText oldPassword;
    @BindView(R.id.new_password_et)
    EditText newPassword;
    @BindView(R.id.new_password_second_et)
    EditText newPasswordRepeat;
    @BindView(R.id.new_username_et)
    EditText newUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_password, container, false);
        setupBinding(this, rootView);

        return rootView;
    }

    @OnClick(R.id.change_username_button)
    public void changeUsernamePressed() {
        UserProfile userProfile = getCurentUserProfile();

        if (!newUsername.getText().toString().isEmpty()) {
            userProfile = new UserProfile(newUsername.getText().toString(), newUsername.getText().toString(), userProfile.getUserPassword());
            saveUserprofile(userProfile);
        }
    }

    @OnClick(R.id.change_password_button)
    public void changePasswordPressed() {
        UserProfile userProfile = getCurentUserProfile();

        if (oldPassword.getText().toString().equals(Objects.requireNonNull(userProfile).getUserPassword())
                && newPassword.getText().toString().equals(newPasswordRepeat.getText().toString())
                && !newPassword.getText().toString().isEmpty()) {
            userProfile = new UserProfile(userProfile.getUserSecretName(), userProfile.getUserId(), newPassword.getText().toString());
            saveUserprofile(userProfile);
        }
    }

    private UserProfile getCurentUserProfile() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader reader = new BufferedReader(new FileReader(BaseApplication.getContext().getFilesDir() + "authentification.txt"));
            UserProfile userProfile = gson.fromJson(reader.readLine(), UserProfile.class);
            reader.close();
            return userProfile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveUserprofile(UserProfile userProfile) {
        try {
            File file = new File(BaseApplication.getContext().getFilesDir() + "authentification.txt");

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            BufferedWriter writer = new BufferedWriter(new FileWriter(BaseApplication.getContext().getFilesDir() + "authentification.txt"));
            writer.write(gson.toJson(userProfile));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Set<BasePresenter> getPresenters() {
        return presenters;
    }
}
