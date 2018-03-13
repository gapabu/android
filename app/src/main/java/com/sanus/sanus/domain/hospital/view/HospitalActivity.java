package com.sanus.sanus.domain.hospital.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.adapter.HospitalAdapter;
import com.sanus.sanus.domain.hospital.data.Hospital;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenter;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenterImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;

import java.util.List;

public class HospitalActivity extends AppCompatActivity implements HospitalView{
    private HospitalPresenter presenter;
    RecyclerView recyclerView;
    HospitalAdapter adapter;
    private ImageView next;

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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, SelectDoctorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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
