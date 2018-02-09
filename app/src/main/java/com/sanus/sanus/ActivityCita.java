package com.sanus.sanus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityCita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
    }

    public void menuBusqueda(View view) {
        Intent intent = new Intent(this, BusquedaDoctor.class);
        startActivity(intent);
    }

    public void menuCita(View view) {
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

    public void nuevaCita(View view) {
        Intent intent = new Intent(this, ActivityCreaCitas.class);
        startActivity(intent);
    }
}
