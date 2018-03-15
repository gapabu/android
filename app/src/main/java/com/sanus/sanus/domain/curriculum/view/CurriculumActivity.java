package com.sanus.sanus.domain.curriculum.view;

import android.content.Intent;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
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
    private final String TAG = this.getClass().getSimpleName();
    private String user_id;

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
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error" + e.getMessage());
                }
                for (final DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        user_id = doc.getDocument().getId();
                        Log.d(TAG, "id c " + user_id);

                        mFirestore.collection("doctores").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    if (task.getResult().exists()){
                                        String especialidad1 = task.getResult().getString("especialidad");
                                        String cedul = task.getResult().getString("cedula");
                                        String cv1 = task.getResult().getString("cv");

                                        mFirestore.collection("usuarios").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                            @Override
                                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                                String nombre = documentSnapshot.getString("nombre");
                                                String apellido = documentSnapshot.getString("apellido");
                                                String image = documentSnapshot.getString("avatar");
                                                String usuario = nombre + " " +apellido;
                                                toolbar = findViewById(R.id.toolbar);
                                                setSupportActionBar(toolbar);
                                                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                                                getSupportActionBar().setTitle(usuario);
                                                Log.d(TAG, "avatar: " + image);
                                            }
                                        });
                                        especialidad.setText(especialidad1);
                                        cedula.setText(cedul);
                                        cv.setText(cv1);

                                    }else {
                                        Log.d(TAG, "Data doen't exist");
                                    }
                                }else{
                                    String error = task.getException().getMessage();
                                    Log.d(TAG, "FIRESTORE retrieve error " + error);
                                }
                            }
                        });

                    }
                }
            }
        });
    }
}


