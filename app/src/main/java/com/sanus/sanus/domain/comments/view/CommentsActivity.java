package com.sanus.sanus.domain.comments.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.adapter.CommentsDoctorAdapter;
import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenterImpl;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommentsActivity extends AppCompatActivity implements CommentsView{
    private CommentsPresenter presenter;
    private RatingBar ratingBar;
    private EditText edNuevoComentario;
    private RecyclerView recyclerView;
    private String idDoct;
    CommentsDoctorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        setUpVariable();
        setUpView();
        presenter.viewComents(idDoct);
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new CommentsPresenterImpl(this);
        }
    }

    private void setUpView() {

        idDoct = getIntent().getStringExtra("idDoctor");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_comments);

        edNuevoComentario = findViewById(R.id.edComentario);
        FloatingActionButton guardarComentario = findViewById(R.id.btnGuardarComentario);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.getRating();
        guardarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSaveData();
                edNuevoComentario.getText().clear();
                ratingBar.setRating(0);
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
                Intent intent = new Intent(this, CurriculumActivity.class);
                intent.putExtra("idDoctor", idDoct);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
        return true;
    }


    @Override
    public void setDataAdapter(List<CommentsDoctor> commentsDoctorList) {
        CommentsDoctorAdapter commentsDoctorAdapter = new CommentsDoctorAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }


    @Override
    public String getComment() {
        return edNuevoComentario.getText().toString();
    }

    @Override
    public String getCalificacion() {
        float valoracion = (ratingBar.getRating()) * 20;
        final int valoracionDoc = (int) valoracion;
        return String.valueOf(valoracionDoc);
    }

    @Override
    public String getIdDoctor() {
        return idDoct = getIntent().getStringExtra("idDoctor");
    }

    @Override
    public void showPhoto(Uri uri, CircleImageView avatar) {
        GlideApp.with(getApplicationContext()).load(uri.toString()).placeholder(R.drawable.user).into(avatar);
    }

}
