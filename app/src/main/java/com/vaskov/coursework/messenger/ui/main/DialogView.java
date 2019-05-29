package com.vaskov.coursework.messenger.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vaskov.coursework.messenger.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogView extends RelativeLayout {

    @BindView(R.id.photo_user_iv)
    ImageView photoImageView;

    @BindView(R.id.username_tv)
    TextView username;

    @BindView(R.id.message_tv)
    TextView message;

    @BindView(R.id.dialog_time_tv)
    TextView time;

    public DialogView(Context context) {
        super(context);

        inflateAndBind(context);
    }

    public DialogView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflateAndBind(context);
    }

    private void inflateAndBind(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.dilaog_view, this);
        }
        ButterKnife.bind(this);
    }

    //TODO: add params
    public void setPhotoImageView() {
        Glide.with(this)
                .load(getContext().getResources().getDrawable(R.drawable.home_illustration_android))
                .into(photoImageView);

        int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());

        photoImageView.getLayoutParams().height = dimensionInDp;
        photoImageView.getLayoutParams().width = dimensionInDp;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setTime(String time) {
        this.time.setText(time);
    }
}
