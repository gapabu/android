package com.sanus.sanus.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;

import java.util.HashMap;
import java.util.Map;

public class CrearCuentaActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button  btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        inputEmail = (EditText) findViewById(R.id.edCorreoE);
        inputPassword = (EditText) findViewById(R.id.edContrasenia);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (Button) findViewById(R.id.btnCrearCuenta);


        auth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingresa un correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Se necesitan minimo 6 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CrearCuentaActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(CrearCuentaActivity.this, "Autenticacion fallida" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(CrearCuentaActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressBar.setVisibility(View.GONE);
                                                    if (task.isSuccessful()) {
                                                        String id = auth.getCurrentUser().getUid();
                                                        String username = inputEmail.getText().toString();
                                                        Map<String, String> userMap = new HashMap<>();
                                                        userMap.put("tipo", "Paciente");

                                                        mFirestore.collection("usuarios").document(id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                //Toast.makeText(CrearCuentaActivity.this, "ok", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Toast.makeText(CrearCuentaActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(CrearCuentaActivity.this);
                                                        builder.setIcon(R.mipmap.ic_launcher)
                                                                .setTitle("Cuenta creada con éxito")
                                                                .setMessage("Tu cuenta a sido activada con éxito");
                                                        AlertDialog alertDialog= builder.create();
                                                        alertDialog.show();
                                                        startActivity(new Intent(CrearCuentaActivity.this, CompletaRegistroActivity.class));
                                                        finish();
                                                    }
                                                }
                                            });
                                }
                            }
                        });

            }
        });
    }


    public void irInicio(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
