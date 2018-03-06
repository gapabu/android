package com.sanus.sanus.domain.curriculum.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.view.CommentsActivity;
import com.sanus.sanus.domain.comments.view.CommentsView;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenterImpl;


public class CurriculumActivity extends AppCompatActivity implements CurriculumView {

    private CurriculumPresenter presenter;

    TextView curricumlum, cedula, especialidad;
    FloatingActionButton goComent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);


        setUpView();
        setUpVariable();
        presenter.init();
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


}
