package com.sanus.sanus.domain.select_hour.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaActivity;
import com.sanus.sanus.domain.select_day.view.SelectDayActivity;
import com.sanus.sanus.domain.select_hour.adapter.SelectHourAdapter;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenterImpl;

import java.util.List;

public class SelectHourActivity extends AppCompatActivity implements SelectHourView{
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    FloatingActionButton skip, next;
    String idHospital, idDoctor, fecha, dia;
    //TimePicker time;
    String hour;
    private RecyclerView recyclerView;
    SelectHourAdapter adapter;

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

        //time = findViewById(R.id.timePicker);

        idHospital = getIntent().getStringExtra("idHospital");
        idDoctor = getIntent().getStringExtra("idDoctor");
        fecha = getIntent().getStringExtra("fecha");
        dia = getIntent().getStringExtra("dia");
        Log.d(TAG, "idHospital=>" + idHospital + " " + "idDoctor=>" + idDoctor + " " + "fecha=>" +fecha + " dia=>" + dia);


        //time = findViewById(R.id.timePicker);

        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        disableButton();
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

      /*  time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay + ":" + minute;
                Log.d(TAG, "this hour: " + hour );
                enableButton();
            }
        });*/

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
    public void next() {
        Intent intent = new Intent(SelectHourActivity.this, ResumeNewCitaActivity.class);
        intent.putExtra("idDoctor", idDoctor);
        intent.putExtra("idHospital", idHospital);
        intent.putExtra("fecha", fecha);
        intent.putExtra("hour", hour);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }

    @Override
    public void previous() {
        startActivity(new Intent(SelectHourActivity.this, SelectDayActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void setDataAdapter(List<SelectHour> commentsDoctorList) {
        SelectHourAdapter commentsDoctorAdapter = new SelectHourAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }
}

