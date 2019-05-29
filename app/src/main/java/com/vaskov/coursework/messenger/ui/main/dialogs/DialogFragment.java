package com.vaskov.coursework.messenger.ui.main.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vaskov.coursework.messenger.R;
import com.vaskov.coursework.messenger.domain.models.Dialog;
import com.vaskov.coursework.messenger.presenters.PresentersFactory;
import com.vaskov.coursework.messenger.presenters.base.BasePresenter;
import com.vaskov.coursework.messenger.presenters.dialogs.DialogsPresenter;
import com.vaskov.coursework.messenger.ui.base.BaseFragment;
import com.vaskov.coursework.messenger.ui.main.MainActivity;

import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class DialogFragment extends BaseFragment implements DialogsPresenter.View {

    @BindView(R.id.dialogs_view_rv)
    RecyclerView dialogsLayout;

    private DialogsRecyclerViewAdapter dialogsRecyclerViewAdapter;

    private DialogsPresenter dialogsPresenter;

    @Override
    protected Set<BasePresenter> getPresenters() {
        presenters.add(dialogsPresenter);

        return presenters;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dialogsPresenter = PresentersFactory.getDialogsPresenter(this);
        dialogsPresenter.setRouter((MainActivity) getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialogs, container, false);
        setupBinding(this, rootView);

        dialogsRecyclerViewAdapter = new DialogsRecyclerViewAdapter(dialog -> dialogsPresenter.dialogSelected(dialog));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dialogsLayout.setLayoutManager(linearLayoutManager);
        dialogsLayout.setAdapter(dialogsRecyclerViewAdapter);

        return rootView;
    }

    @Override
    public void showDialogsList(List<Dialog> dialogs) {
        dialogsRecyclerViewAdapter.setScanResultViews(getContext(), dialogs);
    }
}
