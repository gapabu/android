package com.sanus.sanus.domain.doctor_module.configuration.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.configuration.presenter.ConfigurationDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.configuration.presenter.ConfigurationDoctorPresenterImpl;

public class ConfigurationDoctorFragment extends Fragment implements ConfigurationDoctorFragmentView {

    public static String IDENTIFIER = "CONFIGURATION_DOCTOR_FRAGMENT";
    private ConfigurationDoctorPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuration_doctor, container, false);
        setUpVariable();

        return view;
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new ConfigurationDoctorPresenterImpl(this);
        }
    }
}
