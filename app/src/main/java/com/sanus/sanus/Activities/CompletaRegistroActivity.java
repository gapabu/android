package com.sanus.sanus.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;

public class CompletaRegistroActivity extends AppCompatActivity {
    EditText nombre, apellido, edad;
    Button masculino, femenino;
    private FirebaseAuth auth;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completa_registro);

        nombre = (EditText) findViewById(R.id.edNombre);
        apellido = (EditText) findViewById(R.id.edApellido);
        edad = (EditText) findViewById(R.id.edEdad);
        masculino = (Button) findViewById(R.id.btnMasculino);
        femenino = (Button) findViewById(R.id.btnFemenino);

        auth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }
}
