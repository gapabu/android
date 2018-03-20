package com.sanus.sanus.domain.doctor_module.chat.view;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresent;
import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresentImpl;

public class ChatDoctorFragment extends Fragment implements ChatDoctorFragmentView {
    public static String IDENTIFIER = "CHAT_DOCTOR_FRAGMENT";
    private ChatDoctorPresent present;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_doctor, container, false);
        setUpVariable();

        return view;
    }

    private void setUpVariable() {
        if (present == null){
            present = new ChatDoctorPresentImpl(this);
        }
    }
}
