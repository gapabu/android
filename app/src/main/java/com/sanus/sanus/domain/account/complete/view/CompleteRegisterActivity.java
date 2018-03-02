package com.sanus.sanus.domain.account.complete.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.account.complete.presenter.CompleteRegisterPresenter;
import com.sanus.sanus.domain.account.complete.presenter.CompleteRegisterPresenterImpl;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.utils.alert.AlertUtils;


public class CompleteRegisterActivity extends AppCompatActivity implements CompleteRegisterView {
    private CompleteRegisterPresenter presenter;

    EditText nombre, apellido;
    Button masculino, femenino, guardar;
    Spinner spinnerEdad;
    ImageView imgavatar, imgCamara, imgSave;

    private String edadPosition;

    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completa_registro);

        setUpVariable();
        setUpView();

    }

    private void setUpView() {

        nombre = findViewById(R.id.edNombre);
        apellido = findViewById(R.id.edApellido);
        masculino = findViewById(R.id.btnMasculino);
        femenino = findViewById(R.id.btnFemenino);
        spinnerEdad = findViewById(R.id.spinnerEdad);
        guardar = findViewById(R.id.btnGuardar);

        imgavatar = findViewById(R.id.imgHeader);
        imgCamara = findViewById(R.id.imgCamera);
        imgSave = findViewById(R.id.imgSave);

        items = getResources().getStringArray(R.array.Edad);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEdad.setAdapter(adapter);
        spinnerEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edadPosition = items[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        masculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickGenderMale();
            }
        });

        femenino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickGenderFemale();
            }
        });

        imgCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickImgCamera();
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.uploadFile();
            }
        });

        //guardando datos en firestore
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSaveData();
            }
        });
    }

    private void setUpVariable() {

        if (presenter == null) {
            presenter = new CompleteRegisterPresenterImpl(this);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data, getApplicationContext());
    }

    @Override
    public void setImageAvatar(Bitmap bitmap) {
         imgavatar.setImageBitmap(bitmap);
    }

    @Override
    public void selectMale() {
        masculino.setBackgroundColor(getResources().getColor(R.color.black));
        masculino.setTextColor(getResources().getColor(R.color.text));
        femenino.setBackgroundColor(getResources().getColor(R.color.text));
        femenino.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void selectFemale() {
        femenino.setBackgroundColor(getResources().getColor(R.color.black));
        femenino.setTextColor(getResources().getColor(R.color.text));
        masculino.setBackgroundColor(getResources().getColor(R.color.text));
        masculino.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void showFileChooser(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select"), requestCode);
    }

    @Override
    public void fileStorageReference(StorageReference fileRef, Uri filePath) {
        fileRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        presenter.onSuccess(taskSnapshot);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onFailure();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                presenter.onProgress(taskSnapshot);
            }
        });
    }

    @Override
    public String getName() {
        return nombre.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return apellido.getText().toString().trim();
    }

    @Override
    public String getEdadPosition() {
        return edadPosition;
    }

    @Override
    public void showMessage(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public ProgressDialog getLoading() {
        return AlertUtils.getLoading(this);
    }
}
