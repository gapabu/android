package com.sanus.sanus.domain.select_clinica.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenterImpl;

public class SelectClinicaActivity extends AppCompatActivity implements SelectClinicaView{
    private SelectClinicaPresenter presenter;
    private RecyclerView recyclerView;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.select_hospital);

     setUpVariable();
     setUpView(recyclerView);
 }

    private void setUpVariable(){
     if (presenter == null){
         presenter = new SelectClinicaPresenterImpl(this);
     }
    }

    private void setUpView(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
