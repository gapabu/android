package com.sanus.sanus.domain.select_doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
    RecyclerView recyclerView;
    SelectDoctorAdapter adapter;
    private ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_doctor);
        setUpVariable();
        setUpView();
        presenter.viewComents();
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new SelectDoctorPresenterImpl(this);
        }
    }

    private void setUpView() {

        next = findViewById(R.id.btn_next);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        EditText edbuscador = findViewById(R.id.edbuscador);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectDoctorActivity.this, SelectDayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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
        SelectDoctorAdapter commentsDoctorAdapter = new SelectDoctorAdapter(getApplicationContext(), commentsDoctorList);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

}
