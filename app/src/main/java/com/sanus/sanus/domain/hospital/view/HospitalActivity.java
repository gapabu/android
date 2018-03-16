package com.sanus.sanus.domain.hospital.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.adapter.HospitalAdapter;
import com.sanus.sanus.domain.hospital.data.Hospital;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenter;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenterImpl;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;

import java.util.List;

public class HospitalActivity extends AppCompatActivity implements HospitalView{
    private HospitalPresenter presenter;
    RecyclerView recyclerView;
    HospitalAdapter adapter;
    private ImageView next, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_hospital);
        setUpVariable();
        setUpView();
        presenter.viewComents();
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new HospitalPresenterImpl(this);
        }
    }

    private void setUpView() {

        next = findViewById(R.id.btn_next);
        close = findViewById(R.id.btn_close);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        EditText edbuscador = findViewById(R.id.edbuscador);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, SelectDoctorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
    public void setDataAdapter(List<Hospital> commentsDoctorList) {
        HospitalAdapter commentsDoctorAdapter = new HospitalAdapter(getApplicationContext(), commentsDoctorList);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

}