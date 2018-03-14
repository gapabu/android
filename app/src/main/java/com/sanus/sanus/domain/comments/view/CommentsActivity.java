package com.sanus.sanus.domain.comments.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.adapter.CommentsDoctorAdapter;
import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenterImpl;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity implements CommentsView{
    private CommentsPresenter presenter;
    private RatingBar ratingBar;
    private EditText edNuevoComentario;
    private String id;
    RecyclerView recyclerView;
    CommentsDoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);

        setUpVariable();
        setUpView();
        presenter.viewComents();
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new CommentsPresenterImpl(this);
        }
    }

    private void setUpView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_comments);

        edNuevoComentario = findViewById(R.id.edComentario);
        ImageView guardarComentario = findViewById(R.id.btnGuardarComentario);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.getRating();
        guardarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComments();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, CurriculumActivity.class));
                finish();
                break;
        }
        return true;
    }



    @Override
    public void sendComments() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id = user.getUid();
        }

        final Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int anio = calendar.get(Calendar.YEAR);
        String date = (dia + "/" + (mes + 1) + "/" + anio);
        String comments = edNuevoComentario.getText().toString();
        float valoracion = (ratingBar.getRating()) * 20;
        int valoracionDoc = (int) valoracion;

        Map<String, String> commentMap = new HashMap<>();
        commentMap.put("comentario", comments);
        commentMap.put("fecha", date);
        commentMap.put("calificacion", String.valueOf(valoracionDoc));
        commentMap.put("doctor", "MbisakX6endQjlgSdPRqDcAibpY2");
        commentMap.put("usuario", id);

        mFirestore.collection("comentarios").add(commentMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                startActivity(new Intent(getApplicationContext(), CommentsActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CommentsActivity.this, "error " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setDataAdapter(List<CommentsDoctor> commentsDoctorList) {
        CommentsDoctorAdapter commentsDoctorAdapter = new CommentsDoctorAdapter(getApplicationContext(), commentsDoctorList);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

}
