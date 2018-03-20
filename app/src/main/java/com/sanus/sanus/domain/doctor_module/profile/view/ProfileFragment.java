package com.sanus.sanus.domain.doctor_module.profile.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenterImpl;

public class ProfileFragment extends Fragment implements ProfileFragmentView  {
    public static String IDENTIFIER = "PROFILE_FRAGMENT";
    private ProfileDoctorPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_doctor, container, false);
        setUpVariable();
        return view;
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new ProfileDoctorPresenterImpl(this);
        }
    }
}
