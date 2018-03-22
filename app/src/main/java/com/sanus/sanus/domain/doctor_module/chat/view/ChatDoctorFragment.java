package com.sanus.sanus.domain.doctor_module.chat.view;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.chat.adapter.ContactDoctorAdapter;
import com.sanus.sanus.domain.doctor_module.chat.data.ContactDoctor;
import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresent;
import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresentImpl;

import java.util.List;

public class ChatDoctorFragment extends Fragment implements ChatDoctorFragmentView {
    public static String IDENTIFIER = "CHAT_DOCTOR_FRAGMENT";
    private ChatDoctorPresent present;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_doctor, container, false);
        setUpVariable();
        setUpView(view);
        present.init();
        return view;
    }

    private void setUpVariable() {
        if (present == null){
            present = new ChatDoctorPresentImpl(this);
        }
    }

    private void setUpView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setDataAdapter(List<ContactDoctor> busquedaDoctors) {
        ContactDoctorAdapter busquedaDoctorAdapteradapter = new ContactDoctorAdapter(getContext(), busquedaDoctors, present);
        recyclerView.setAdapter(busquedaDoctorAdapteradapter);
        busquedaDoctorAdapteradapter.notifyDataSetChanged();
    }
}
