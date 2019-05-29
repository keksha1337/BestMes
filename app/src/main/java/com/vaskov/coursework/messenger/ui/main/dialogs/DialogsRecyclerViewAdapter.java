package com.vaskov.coursework.messenger.ui.main.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.ui.main.DialogView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class DialogsRecyclerViewAdapter extends RecyclerView.Adapter<DialogsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Dialog> dialogList;
    private DialogAdapterClickListener listener;

    public DialogsRecyclerViewAdapter(DialogAdapterClickListener listener) {
        this.listener = listener;
        dialogList = new ArrayList<>();
    }

    public void setScanResultViews(Context context, List<Dialog> dialogList) {
        this.context = context;
        this.dialogList.clear();
        this.dialogList.addAll(dialogList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDialogView(dialogList.get(position));
        holder.setOnClickListener(aView -> {
            if (listener != null) {
                listener.onDialogSelected(dialogList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dialogList.size();
    }

    public interface DialogAdapterClickListener {
        void onDialogSelected(Dialog dialog);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dialog_item_srv)
        DialogView dialogView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setDialogView(Dialog dialog) {
            dialogView.setPhotoImageView();
            dialogView.setMessage(dialog.getLastMessage());
            dialogView.setUsername(dialog.getUsername());
            dialogView.setTime(dialog.getTimeOfLastMessage());
        }

        void setOnClickListener(RelativeLayout.OnClickListener aListener) {
            dialogView.setOnClickListener(aListener);
        }
    }

}
