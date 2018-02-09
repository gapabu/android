package com.sanus.sanus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Mireya on 01/02/2018.
 */

public class BusquedaDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_doctor);

    }

    public void mbusqueda(View view) {

    }

    public void menuCita(View view) {
        Intent intent = new Intent(this, ActivityCita.class);
        startActivity(intent);
    }

    public void menuForo(View view) {
        Intent intent = new Intent(this, ActivityForo.class);
        startActivity(intent);
    }

    public void menuAyuda(View view) {
        Intent intent = new Intent(this, ActivityAyuda.class);
        startActivity(intent);
    }

    public void menuConfig(View view) {
        Intent intent = new Intent(this, ActivityConfiguracion.class);
        startActivity(intent);
    }

    public void verCV(View view) {
        Intent intent = new Intent(this, CurriculumDoctor.class);
        startActivity(intent);
    }
}
