package com.vaskov.coursework.messenger.ui.main.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.UserData;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;

import java.util.Objects;
import java.util.Set;

import butterknife.BindView;

public class UserFragment extends BaseFragment {

    @BindView(R.id.user_fragment_edit_button)
    ImageView editButton;
    @BindView(R.id.user_fragment_username_tv)
    EditText username;
    @BindView(R.id.user_fragment_photo_iv)
    ImageView photo;
    @BindView(R.id.user_fragment_phone_tv)
    EditText phone;
    @BindView(R.id.user_fragment_user_id_tv)
    EditText userId;

    private UserData userData;
    private boolean isEditable;

    public UserFragment(UserData userData, boolean isEditable) {
        this.userData = userData;
        this.isEditable = isEditable;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        setupBinding(this, rootView);

        if (isEditable) {
            editButton.setVisibility(View.VISIBLE);
        }

        Glide.with(this)
                .load(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.home_illustration_android))
                .into(photo);

        username.setText(userData.getUsername());
        phone.setText(userData.getPhoneNumber());
        userId.setText(userData.getUserId());

        return rootView;
    }

    @Override
    protected Set<BasePresenter> getPresenters() {
        return presenters;
    }
}
