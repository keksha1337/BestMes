package com.vaskov.coursework.messenger.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.ui.main.dialogs.DialogFragment;
import com.vaskov.coursework.messenger.ui.main.home.HomeFragment;

public class MainAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENTS_AMOUNT = 2;
    private static final int DIALOGS = 0;
    private static final int SETTINGS = 1;

    private Context context;

    public MainAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case DIALOGS:
                fragment = new DialogFragment();
                break;
            case SETTINGS:
                fragment = new HomeFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENTS_AMOUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle = "";
        switch (position) {
            case 0:
                tabTitle = context.getString(R.string.dialogs);
                break;
            case 1:
                tabTitle = context.getString(R.string.settings);
                break;
        }
        return tabTitle;
    }
}