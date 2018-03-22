package com.sanus.sanus.domain.chat.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.chat.adapter.ContactUserAdapter;
import com.sanus.sanus.domain.chat.data.ContactUser;
import com.sanus.sanus.domain.chat.presenter.ChatPresenter;
import com.sanus.sanus.domain.chat.presenter.ChatPresenterImpl;

import java.util.List;

public class ChatFragment extends Fragment implements ChatFragmentView{

    public static String IDENTIFIER = "CHAT_FRAGMENT";
    private ChatPresenter presenter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        setUpVariable();
        setUpView(view);
        presenter.init();
        return view;
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new ChatPresenterImpl(this);
        }
    }

    private void setUpView(View view){

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void setDataAdapter(List<ContactUser> busquedaDoctors) {
        ContactUserAdapter busquedaDoctorAdapteradapter = new ContactUserAdapter(getContext(), busquedaDoctors, presenter);
        recyclerView.setAdapter(busquedaDoctorAdapteradapter);
        busquedaDoctorAdapteradapter.notifyDataSetChanged();
    }
}

