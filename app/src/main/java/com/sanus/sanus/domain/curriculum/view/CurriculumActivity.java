package com.sanus.sanus.domain.curriculum.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
    TextView cedula, especialidad, cv;
    private CircleImageView setupImage;
    private final String TAG = this.getClass().getSimpleName();
    private String user_id, image;
    private String idUs;

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
        toolbar = findViewById(R.id.toolbar);
        cedula = findViewById(R.id.tvCedula);
        especialidad = findViewById(R.id.tvEspecialidad);
        cv = findViewById(R.id.tvCv);
        setupImage = findViewById(R.id.setup_image);
        goComent = findViewById(R.id.floatinIrComentarios);

        goComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurriculumActivity.this, CommentsActivity.class);
                intent.putExtra("id", idUs);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
        return true;
    }

    private void showImage(){
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(CurriculumActivity.this).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(CurriculumActivity.this, "Error al traer foto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializedData() {
        idUs = getIntent().getStringExtra("id");
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").document(idUs).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String especialidad1 = task.getResult().getString("especialidad");
                        String cedul = task.getResult().getString("cedula");
                        String cv1 = task.getResult().getString("cv");

                        mFirestore.collection("usuarios").document(idUs).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                image = documentSnapshot.getString("avatar");
                                showImage();
                                String usuario = nombre + " " +apellido;
                                setSupportActionBar(toolbar);
                                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                                getSupportActionBar().setTitle(usuario);
                            }
                        });
                        especialidad.setText(especialidad1);
                        cedula.setText(cedul);
                        cv.setText(cv1);
                    }
                }
            }
        });
    }
}


