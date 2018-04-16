package com.sanus.sanus.domain.account.complete.view;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.account.complete.presenter.CompleteRegisterPresenter;
import com.sanus.sanus.domain.account.complete.presenter.CompleteRegisterPresenterImpl;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainActivityDoctor;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.utils.alert.AlertUtils;
import com.sanus.sanus.utils.glide.GlideApp;
import com.sanus.sanus.utils.keyboard.KeyboardUtil;

import java.util.ArrayList;


public class CompleteRegisterActivity extends AppCompatActivity implements CompleteRegisterView {
    private final String TAG = getClass().getSimpleName();
    private CompleteRegisterPresenter presenter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText nombre, apellido;
    private Button masculino, femenino, save;
    private Spinner spinnerEdad;
    private ImageView imgavatar;
    private String edadPosition, id, img;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completa_registro);

        setUpVariable();
        setUpView();
        presenter.setUpSpinner();
        init();

    }

    private void setUpView() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {idUser = user.getUid();}

        nombre = findViewById(R.id.edNombre);
        apellido = findViewById(R.id.edApellido);
        masculino = findViewById(R.id.btnMasculino);
        femenino = findViewById(R.id.btnFemenino);
        spinnerEdad = findViewById(R.id.spinnerEdad);
        save = findViewById(R.id.btnGuardar);
        RelativeLayout rlHeader = findViewById(R.id.rlHeader);
        imgavatar = findViewById(R.id.imgProfile);
        ImageView imgCamara = findViewById(R.id.imgCamera);

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSaveData();
            }
        });

        rlHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.validateButtonEnable();
            }
        });

        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.validateButtonEnable();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                DocumentReference usuarios = db.collection("usuarios").document(idUser);
                usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String tipo = document.getString("tipo");
                                if (tipo.equals("Medico")) {
                                    goMainDoctor();
                                }
                                if (tipo.equals("Paciente")) {
                                    goMain();
                                }
                            }
                        }
                    }
                });
                break;
        }
        return true;
    }
    private void hideKeyboard() {
        KeyboardUtil.hide(this);
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
        masculino.setTextColor(getResources().getColor(R.color.white));
        femenino.setBackgroundColor(getResources().getColor(R.color.white));
        femenino.setTextColor(getResources().getColor(R.color.black));

    }

    @Override
    public void selectFemale() {
        femenino.setBackgroundColor(getResources().getColor(R.color.black));
        femenino.setTextColor(getResources().getColor(R.color.white));
        masculino.setBackgroundColor(getResources().getColor(R.color.white));
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
    public void setListSpinner(ArrayList<String> ageList) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, ageList);
        adapter.setDropDownViewResource(R.layout.list_item_spinner);
        spinnerEdad.setAdapter(adapter);
        spinnerEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edadPosition = spinnerEdad.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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
    public void enableButton() {
        save.setEnabled(true);
    }

    @Override
    public void disableButton() {
        save.setEnabled(false);
    }

    @Override
    public void showMessage(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public ContentResolver getContentResolve() {
        return getContentResolver();
    }

    @Override
    public void showImage(Bitmap bitmap) {
        if (bitmap!=null){
            GlideApp.with(this).load(bitmap).apply(RequestOptions.circleCropTransform()).into(imgavatar);

        }
    }

    @Override
    public ProgressDialog getLoading() {
        return AlertUtils.getLoading(this);
    }

    @Override
    public void goMainDoctor() {
        startActivity(new Intent(this, MainActivityDoctor.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    private void showImage(){
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(img).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                GlideApp.with(getApplicationContext()).load(uri.toString()).apply(RequestOptions.circleCropTransform()).into(imgavatar);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d(TAG, "Error al traer foto");
            }
        });
    }
    private void init(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
           id = user.getUid();
        }
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("usuarios").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        nombre.setText(task.getResult().getString("nombre"));
                        apellido.setText(task.getResult().getString("apellido"));
                        img = task.getResult().getString("avatar");
                        showImage();
                        Toolbar toolbar = findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setTitle(" ");

                    }else{
                        Log.d(TAG, "no existe");
                    }
                }else {
                    String error = task.getException().getMessage();
                    Log.d(TAG, error);
                }
            }
        });

    }
}
