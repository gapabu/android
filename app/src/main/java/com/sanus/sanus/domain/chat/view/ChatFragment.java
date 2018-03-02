package com.sanus.sanus.domain.chat.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.chat.presenter.ChatPresenter;
import com.sanus.sanus.domain.chat.presenter.ChatPresenterImpl;

public class ChatFragment extends Fragment implements ChatFragmentView{

    public static String IDENTIFIER = "CHAT_FRAGMENT";
    private ChatPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        setUpVariable();

        return view;
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new ChatPresenterImpl(this);
        }
    }
}
