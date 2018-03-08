package com.sanus.sanus.domain.select_doctor.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenterImpl;

public class SelectDoctorActivity extends AppCompatActivity implements SelectDoctorView {

    private SelectDoctorPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_doctor);

        setUpVariable();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectDoctorPresenterImpl(this);
        }
    }
}
