package com.vaskov.coursework.messenger.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;

import java.util.Set;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_car_iv)
    ImageView carImageView;

    @Override
    protected Set<BasePresenter> getPresenters() {
        return presenters;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setupBinding(this, rootView);

        return rootView;
    }
}
