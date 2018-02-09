package com.sanus.sanus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurriculumDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_doctor);
    }

    public void irValoraciones(View view) {
        Intent intent = new Intent(this, ValoracionesDoc.class);
        startActivity(intent);

    }
}
