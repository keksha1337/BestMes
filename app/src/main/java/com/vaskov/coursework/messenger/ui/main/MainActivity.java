package com.vaskov.coursework.messenger.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.domain.routers.dialogdetail.DialogDetailRouter;
import com.vaskov.coursework.messenger.domain.routers.dialogs.DialogsRouter;
import com.vaskov.coursework.messenger.ui.main.dialogs.DialogDetailFragment;
import com.vaskov.coursework.messenger.ui.main.dialogs.DialogFragment;
import com.vaskov.coursework.messenger.ui.main.home.HomeFragment;
import com.vaskov.coursework.messenger.ui.main.settings.SettingsFragment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements DialogsRouter, DialogDetailRouter {

    private static final int DIALOGS = 0;
    private static final int SETTINGS = 1;
    private static final int DIALOG_DETAIL = 2;

    @BindView(R.id.main_fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.main_tabs)
    TabLayout tabLayout;

    private Unbinder unbinder;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFilesDir() + "authentification.txt"));
            tabLayoutSetup();

            showDialogs();
        } catch (IOException e) {
            showAuthentification();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void tabLayoutSetup() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.dialogs), DIALOGS, true);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.settings), SETTINGS, false);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                navigateTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (currentTab == DIALOG_DETAIL) {
                    navigateTab(tab.getPosition());
                }
            }
        });
    }

    private void navigateTab(int position) {
        switch (position) {
            case DIALOGS:
                showDialogs();
                break;
            case SETTINGS:
                showSettings();
                break;
            default:
                break;
        }
    }

    private void showSettings() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, new SettingsFragment());
        transaction.commit();

        currentTab = SETTINGS;
    }


    @Override
    public void onBackPressed() {
        if (currentTab == DIALOG_DETAIL) {
            showDialogs();
        } else {
            super.onBackPressed();
        }
    }

    private void showDialogs() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, new DialogFragment());
        transaction.commit();

        currentTab = DIALOGS;
    }

    public void showAuthentification() {
        Intent intent = new Intent(this, AuthentificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void showDialogDetail(Dialog dialog) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, new DialogDetailFragment(dialog));
        transaction.commit();

        currentTab = DIALOG_DETAIL;
    }

    @Override
    public void showUserData(UserData userData) {
        showSettings();
    }
}
