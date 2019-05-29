package com.vaskov.coursework.messenger.ui.main.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.executor.ThreadExecutor;
import com.vaskov.coursework.messenger.domain.interactors.dialogdetail.DialogDetailInteractorImpl;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.domain.models.DialogDetails;
import com.vaskov.coursework.messenger.domain.models.Message;
import com.vaskov.coursework.messenger.domain.repository.dialogdetail.HardcodedDialogDetailRepositoryImpl;
import com.vaskov.coursework.messenger.domain.repository.userdata.HardcodedUserDataRepositoryImpl;
import com.vaskov.coursework.messenger.domain.threading.MainThreadImpl;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.presenters.dialogdetail.DialogDetailPresenter;
import com.vaskov.coursework.messenger.presenters.dialogdetail.DialogDetailPresenterImpl;
import com.vaskov.coursework.messenger.ui.application.BaseApplication;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;
import com.vaskov.coursework.messenger.ui.main.MainActivity;
import com.vaskov.coursework.messenger.ui.main.user.UserFragment;

import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogDetailFragment extends BaseFragment implements DialogDetailPresenter.View {

    @BindView(R.id.fragment_dialog_detail_main_layout_rl)
    RelativeLayout mainLayout;
    @BindView(R.id.dialog_detail_user_data_rl)
    RelativeLayout userDataLayout;
    @BindView(R.id.dialog_detail_sv)
    ScrollView scrollView;
    @BindView(R.id.dialog_detail_ll)
    LinearLayout layout;
    @BindView(R.id.dialog_detail_last_seen_tv)
    TextView lastSeen;
    @BindView(R.id.dialog_detail_photo_iv)
    ImageView photo;
    @BindView(R.id.dialog_detail_username_tv)
    TextView username;
    @BindView(R.id.message_text_et)
    EditText mesTextEdit;

    private DialogDetailPresenter dialogDetailPresenter;
    private Dialog dialog;

    public DialogDetailFragment(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    protected Set<BasePresenter> getPresenters() {
        presenters.add(dialogDetailPresenter);
        return presenters;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dialogDetailPresenter = new DialogDetailPresenterImpl(this,
                new DialogDetailInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                        new HardcodedDialogDetailRepositoryImpl(BaseApplication.getContext(), new HardcodedUserDataRepositoryImpl())));
        dialogDetailPresenter.setRouter((MainActivity) getActivity());
        dialogDetailPresenter.setDialog(dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_detail, container, false);
        setupBinding(this, rootView);

        return rootView;
    }

    @Override
    public void showDialogDetail(DialogDetails dialogDetails) {
        Glide.with(this)
                .load(Objects.requireNonNull(getContext()).getResources().getDrawable(R.drawable.home_illustration_android))
                .into(photo);

        userDataLayout.setOnClickListener(v -> {
            FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
            transaction.replace(R.id.fragment_dialog_detail_main_layout_rl, new UserFragment(dialogDetails.getUserData(), false));
            transaction.commit();
        });

        username.setText(dialogDetails.getUserData().getUsername());
        lastSeen.setText(dialogDetails.getUserData().getLastSeen());

        for (Message message : dialogDetails.getMessages()) {
            TextView textView = new TextView(getContext());
            textView.setText(String.format("    %s    ", message.getText()));
            textView.setTextSize(20);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = 16;

            if (message.getOwnersUserId().equals(dialogDetails.getUserData().getUserId())) {
                layoutParams.gravity = Gravity.END;
                layoutParams.rightMargin = 16;
                layoutParams.leftMargin = 100;
                textView.setGravity(View.TEXT_ALIGNMENT_VIEW_START);
                textView.setBackground(getResources().getDrawable(R.drawable.dialog_view_image_corner));
                textView.setTextColor(getResources().getColor(R.color.lightSage));
            } else {
                layoutParams.gravity = Gravity.START;
                layoutParams.leftMargin = 16;
                layoutParams.rightMargin = 100;
                textView.setGravity(View.TEXT_ALIGNMENT_VIEW_END);
                textView.setBackground(getResources().getDrawable(R.drawable.accuracy_shape));
                textView.setTextColor(getResources().getColor(R.color.darkSlateBlue));
            }
            textView.setLayoutParams(layoutParams);

            layout.addView(textView);
        }
    }

    @Override
    public void onSendMes() {
        dialogDetailPresenter.resume();
    }

    @OnClick(R.id.send_iv)
    public void pressedSendMes() {
        if (!mesTextEdit.getText().toString().isEmpty()) {
            layout.removeAllViews();
            dialogDetailPresenter.pressedSendMes(mesTextEdit.getText().toString());
            mesTextEdit.setText("");
        }
    }
}
