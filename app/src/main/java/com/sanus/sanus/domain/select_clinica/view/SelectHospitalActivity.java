package com.sanus.sanus.domain.select_clinica.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_clinica.adapter.SelectClinicaAdapter;
import com.sanus.sanus.domain.select_clinica.data.ClinicaData;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenterImpl;

import java.util.List;

public class SelectHospitalActivity extends AppCompatActivity implements SelectClinicaView{
    private SelectClinicaPresenter presenter;
    RecyclerView recyclerView;
    SelectClinicaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_hospital);
        
        setUpVariable();
        setUpView();
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectClinicaPresenterImpl(this);
        }
    }

    @Override
    public void setDataAdapter(List<ClinicaData> clinicaDataList) {
        SelectClinicaAdapter selectClinicaAdapter = new SelectClinicaAdapter(getApplicationContext(), clinicaDataList);
        recyclerView.setAdapter(selectClinicaAdapter);
        selectClinicaAdapter.notifyDataSetChanged();
    }
}