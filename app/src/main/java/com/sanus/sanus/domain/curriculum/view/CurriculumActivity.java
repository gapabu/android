package com.sanus.sanus.domain.curriculum.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.view.CommentsActivity;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenterImpl;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class CurriculumActivity extends AppCompatActivity implements CurriculumView {

    private CurriculumPresenter presenter;
    ImageView goComent;
    private Toolbar toolbar;
    TextView curricumlum, cedula, especialidad, cv;
    private CircleImageView setupImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        setUpVariable();
        setUpView();
        initializedData();
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new CurriculumPresenterImpl(this);
        }
    }

    public void setUpView() {
        curricumlum = findViewById(R.id.tvCv);
        cedula = findViewById(R.id.tvCedula);
        especialidad = findViewById(R.id.tvEspecialidad);
        cv = findViewById(R.id.tvCv);
        setupImage = findViewById(R.id.setup_image);
        goComent = findViewById(R.id.floatinIrComentarios);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("nombre");

        goComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurriculumActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return true;
    }

    private void initializedData() {
        //datosDoctorList = new ArrayList<>();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("doctores").document("pjfa24XK9caHEFOG0kyQIl1FVAn1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        //Toast.makeText(CurriculumActivity.this, "Data exist", Toast.LENGTH_SHORT).show();
                        String nombre = task.getResult().getString("nombre");
                        String especialidad1 = task.getResult().getString("especialidad");
                        String cedul = task.getResult().getString("cedula");
                        String cv1 = task.getResult().getString("cv");
                        String image = task.getResult().getString("avatar");
                        especialidad.setText(especialidad1);
                        cedula.setText(cedul);
                        cv.setText(cv1);

                        toolbar = (Toolbar) findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setTitle(nombre);

                        String url = "https://firebasestorage.googleapis.com/v0/b/sanus-27.appspot.com/o/avatar%2FMbisakX6endQjlgSdPRqDcAibpY2.png?alt=media&token=049741fa-58c0-4566-804b-703f50b92070";
                        Picasso.with(CurriculumActivity.this).load(url).placeholder(R.drawable.default_image).into(setupImage);


                    } else {
                        Toast.makeText(CurriculumActivity.this, "Data doen't exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(CurriculumActivity.this, "FIRESTORE retrieve error " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


