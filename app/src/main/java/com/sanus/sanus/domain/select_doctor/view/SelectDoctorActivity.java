package com.sanus.sanus.domain.select_doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.view.HospitalActivity;
import com.sanus.sanus.domain.select_day.view.SelectDayActivity;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenterImpl;

public class SelectDoctorActivity extends AppCompatActivity implements SelectDoctorView {

    private SelectDoctorPresenter presenter;
    private ImageView skip, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_doctor);

        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectDoctorPresenterImpl(this);
        }
    }

    private void setUpView() {
       skip = findViewById(R.id.btn_skip);
       next = findViewById(R.id.btn_next);
       skip.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(SelectDoctorActivity.this, HospitalActivity.class));
               overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
               finish();
           }
       });

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(SelectDoctorActivity.this, SelectDayActivity.class));
               overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
               finish();
           }
       });
    }
}
