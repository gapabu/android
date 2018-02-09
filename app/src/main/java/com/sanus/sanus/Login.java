package com.sanus.sanus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, BusquedaDoctor.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.edCorreo);
        inputPassword = (EditText) findViewById(R.id.edContraseña);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btnInicio);
        btnSignup = (Button) findViewById(R.id.btnCrearCuenta);
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, CrearCuenta.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Ingresa un correo electronico", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Ingresa una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.password_corta));
                                    } else {
                                        Toast.makeText(Login.this, getString(R.string.autenticacion_fallida), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login.this, BusquedaDoctor.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }


    public void irCrearCuentaGoogle(View view) {
        Toast.makeText(this, "Crea cuenta con google", Toast.LENGTH_SHORT).show();
    }

    public void irFacebook(View view) {
        Toast.makeText(this, "Crea cuenta facebook", Toast.LENGTH_SHORT).show();
    }

    public class KeyboardUtil {

        public void hide(Activity activity){
            if(activity.getCurrentFocus()!=null) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}
