package com.sanus.sanus.domain.resume_new_cita.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.citas.view.CitasFragment;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenterImpl;

public class ResumeNewCitaActivity extends AppCompatActivity implements ResumeNewCitaView{
    private final String TAG= this.getClass().getSimpleName();
    private ResumeNewCitaPresenter presenter;
    FloatingActionButton cerrarCita, guardarCita;

    String idHospital, idDoctor, fecha, hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_new_cita);

        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new ResumeNewCitaPresenterImpl(this);
        }
    }

    private void setUpView(){

        idHospital = getIntent().getStringExtra("idHospital");
        idDoctor = getIntent().getStringExtra("idDoctor");
        fecha = getIntent().getStringExtra("fecha");
        hour = getIntent().getStringExtra("hour");
        Log.d(TAG, "idHospital=>" + idHospital + " " + "idDoctor=>" + idDoctor + " " + "fecha=>" +fecha + " " + "hora=>" + hour);

        cerrarCita = findViewById(R.id.closeCita);
        guardarCita = findViewById(R.id.saveCita);

        cerrarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goActivity();
            }
        });

        guardarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity();
            }
        });
    }

    public void goActivity(){
        Intent intent = new Intent(ResumeNewCitaActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
