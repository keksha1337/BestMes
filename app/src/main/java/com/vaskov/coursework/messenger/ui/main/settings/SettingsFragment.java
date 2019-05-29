package com.vaskov.coursework.messenger.ui.main.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;
import com.vaskov.coursework.messenger.ui.main.MainActivity;

import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {

    @BindView(R.id.change_user)
    TextView changeUser;
    @BindView(R.id.change_password)
    TextView changePassword;
    @BindView(R.id.main_settings_ll)
    LinearLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        setupBinding(this, rootView);

        return rootView;
    }

    @SuppressLint("ShowToast")
    @OnClick(R.id.change_user)
    void changeUserPressed() {
        ((MainActivity) Objects.requireNonNull(getActivity())).showAuthentification();
    }

    @SuppressLint("ShowToast")
    @OnClick(R.id.change_password)
    void setChangePasswordPressed() {

    }

    @Override
    protected Set<BasePresenter> getPresenters() {
        return presenters;
    }
}
