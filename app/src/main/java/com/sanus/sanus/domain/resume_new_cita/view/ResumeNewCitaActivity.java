package com.sanus.sanus.domain.resume_new_cita.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenterImpl;
import com.sanus.sanus.utils.alert.AlertUtils;
import com.sanus.sanus.utils.alert.CallbackAlert;
import com.sanus.sanus.utils.glide.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResumeNewCitaActivity extends AppCompatActivity implements ResumeNewCitaView, CallbackAlert {
    private final String TAG= this.getClass().getSimpleName();
    private ResumeNewCitaPresenter presenter;
    private CircleImageView avatar;
    private String idHospital, idDoctor, fecha, hour, idDocument;
    private TextView nameClinic, directionClinic, nameDoctor, specialty, hora, date;
    String  idFecha, idHora;
    FloatingActionButton cerrarCita, guardarCita;

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
        idDocument = getIntent().getStringExtra("idDocument");
        idFecha = getIntent().getStringExtra("idFecha");
        idHora = getIntent().getStringExtra("idHora");
        Log.d(TAG, "idHospital=>" + idHospital + " " + "idDoctor=>" + idDoctor + " " + "fecha=>"
                +fecha + " " + " hora=>" + hour + " idDocumet=> " + idDocument + " idFecha=>" + idFecha + " idHora=>" + idHora);

        presenter.viewDataDoctor(idDoctor);
        presenter.viewDataHospital(idHospital);

        nameClinic = findViewById(R.id.nameClinic);
        directionClinic = findViewById(R.id.directionClinic);
        nameDoctor = findViewById(R.id.nameDoctor);
        specialty = findViewById(R.id.specialty);
        date = findViewById(R.id.fechaCite);
        hora = findViewById(R.id.horaCite);
        avatar = findViewById(R.id.avatar);
        setDate();
        setHour();

        cerrarCita = findViewById(R.id.closeCita);
        guardarCita = findViewById(R.id.saveCita);

        cerrarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCancelAppointment();
            }
        });

        guardarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaveCita();
            }
        });
    }

    @Override
    public String setDate() {
        date.setText(fecha);
        return String.valueOf(date);
    }

    @Override
    public String setHour() {
        hora.setText(hour);
        return String.valueOf(hora);
    }

    @Override
    public String setNameDoctor(String nameDr) {
        nameDoctor.setText(nameDr);
        return String.valueOf(nameDoctor);
    }

    @Override
    public String setSpecialty(String especialidad) {
        specialty.setText(especialidad);
        return String.valueOf(specialty);
    }

    @Override
    public String setNameHospital(String nameHos) {
        nameClinic.setText(nameHos);
        return String.valueOf(nameClinic);
    }

    @Override
    public String setDirectionHospital(String direction) {
        directionClinic.setText(direction);
        return String.valueOf(directionClinic);
    }

    @Override
    public void acceptAlert() {
        presenter.addAppointment(idHospital, idDoctor, fecha, hour, idDocument);
    }

    @Override
    public void cancelAlert() {}


    private void showSaveCita() {
        AlertUtils alertUtils = new AlertUtils(this);
        alertUtils.configureAlert(this, getString(R.string.guardar_cita));
    }

    @Override
    public void goActivity() {
        Intent intent = new Intent(ResumeNewCitaActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void alertSuccessAppoitment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setCancelable(false);
        builder.setMessage(R.string.cita_exitosa);

        builder.setPositiveButton(R.string.button_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goActivity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showPhoto(Uri uri) {
        GlideApp.with(this).load(uri.toString()).placeholder(R.drawable.user).into(avatar);
    }

    public void alertCancelAppointment(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setCancelable(false);
        builder.setMessage(R.string.cancelar_cita);

        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton(R.string.button_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteAppointment(idDocument);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
