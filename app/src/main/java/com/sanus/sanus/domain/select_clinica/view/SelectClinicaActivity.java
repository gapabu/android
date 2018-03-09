package com.sanus.sanus.domain.select_clinica.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_clinica.adapter.SelectClinicaAdapter;
import com.sanus.sanus.domain.select_clinica.data.ClinicaData;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenterImpl;

import java.util.List;

public class SelectClinicaActivity extends AppCompatActivity implements SelectClinicaView{
    private SelectClinicaPresenter presenter;
    private RecyclerView recyclerView;
    SelectClinicaAdapter adapter;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.select_hospital);

     setUpVariable();
     setUpView();
     presenter.init();





 }

    private void setUpVariable(){
     if (presenter == null){
         presenter = new SelectClinicaPresenterImpl(this);
     }
    }

    private void setUpView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setAdapter(adapter);
    }

    @Override
    public void setDataAdapter(List<ClinicaData> clinicaDataList) {
        SelectClinicaAdapter clinicaAdapter = new SelectClinicaAdapter(getApplicationContext(), clinicaDataList);
        clinicaAdapter.notifyDataSetChanged();
    }
}
