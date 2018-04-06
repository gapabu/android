package com.sanus.sanus.domain.select_doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.view.HospitalActivity;
import com.sanus.sanus.domain.select_day.view.SelectDayActivity;
import com.sanus.sanus.domain.select_doctor.adapter.SelectDoctorAdapter;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenterImpl;

import java.util.List;

public class SelectDoctorActivity extends AppCompatActivity implements SelectDoctorView {

    private SelectDoctorPresenter presenter;
    private RecyclerView recyclerView;
    private String TAG = this.getClass().getSimpleName();
    SelectDoctorAdapter adapter;
    FloatingActionButton next, skip;
    private String idHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_doctor);
        setUpVariable();
        setUpView();
        presenter.viewDoctor(idHospital);
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new SelectDoctorPresenterImpl(this);
        }
    }

    private void setUpView() {
        idHospital = getIntent().getStringExtra("idHospital");
        Log.d(TAG, "idHospital: " +idHospital);


        next = findViewById(R.id.btn_next);
        skip = findViewById(R.id.btn_skip);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        EditText edbuscador = findViewById(R.id.edbuscador);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               previous();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });



        edbuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.buscador(s.toString());
            }
        });
    }


    @Override
    public void setDataAdapter(List<SelectDoctor> commentsDoctorList) {
        SelectDoctorAdapter commentsDoctorAdapter = new SelectDoctorAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

    @Override
    public void goSelectDay(String value) {
        Intent intent = new Intent(this, SelectDayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("idDoctor", value);
        intent.putExtra("idHospital", idHospital);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);
    }

    @Override
    public void next() {
        Intent intent = new Intent(this, SelectDayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("idDoctor", "idDoctor");
        intent.putExtra("idHospital", idHospital);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);
    }

    @Override
    public void previous() {
        startActivity(new Intent(SelectDoctorActivity.this, HospitalActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

}
