package com.sanus.sanus.domain.select_hour.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaActivity;
import com.sanus.sanus.domain.select_day.view.SelectDayActivity;
import com.sanus.sanus.domain.select_hour.adapter.SelectHourAdapter;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHourActivity extends AppCompatActivity implements SelectHourView{
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private String idHospital, idDoctor, fecha, dia, idDocument;
    private FloatingActionButton next;
    FloatingActionButton previous;
    SelectHourAdapter adapter;
    String idFecha, idHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_hour);
        setUpVariable();
        setUpView();
        presenter.viewSchedules(idDoctor, dia);
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectHourPresenterImpl(this);
        }
    }

    private void setUpView() {
        idHospital = getIntent().getStringExtra("idHospital");
        idDoctor = getIntent().getStringExtra("idDoctor");
        fecha = getIntent().getStringExtra("fecha");
        dia = getIntent().getStringExtra("dia");
        idDocument = getIntent().getStringExtra("idDocument");
        Log.d(TAG, "idHospital=>" + idHospital + " " + "idDoctor=>" + idDoctor + " "
                + "fecha=>" +fecha + " dia=>" + dia + " idDocumement=>" + idDocument);

        previous = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        disableButton();
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.deleteAppointment(idDocument);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void enableButton() {
        next.setEnabled(true);
        next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public void disableButton() {
        next.setEnabled(false);
    }

    @Override
    public void next(final String value) {
        presenter.addAppointment(idHospital, idDoctor, fecha, value, idDocument);
        Intent intent = new Intent(SelectHourActivity.this, ResumeNewCitaActivity.class);
        intent.putExtra("idDoctor", idDoctor);
        intent.putExtra("idHospital", idHospital);
        intent.putExtra("fecha", fecha);
        intent.putExtra("hour", value);
        intent.putExtra("idDocument", idDocument);
        intent.putExtra("idFecha", idFecha);
        intent.putExtra("idHora", idHora);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }

    @Override
    public void previous() {
        Intent intent = new Intent(SelectHourActivity.this, SelectDayActivity.class);
        intent.putExtra("idDoctor", idDoctor);
        intent.putExtra("idHospital", idHospital);
        intent.putExtra("fecha", fecha);
        intent.putExtra("idDocument", idDocument);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);
        finish();
    }

    @Override
    public void setDataAdapter(List<SelectHour> busquedaDoctors) {
        SelectHourAdapter busquedaDoctorAdapteradapter = new SelectHourAdapter(getApplicationContext(), busquedaDoctors, presenter);
        recyclerView.setAdapter(busquedaDoctorAdapteradapter);
        busquedaDoctorAdapteradapter.notifyDataSetChanged();
    }

    @Override
    public void setDate(String idHora, String idFecha) {
        Intent intent = new Intent(SelectHourActivity.this, SelectHourActivity.class);
        intent.putExtra("idFecha", idFecha);
        intent.putExtra("idHora", idHora);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);
        finish();
    }
}

