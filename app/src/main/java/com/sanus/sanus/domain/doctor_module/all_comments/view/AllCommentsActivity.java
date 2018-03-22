package com.sanus.sanus.domain.doctor_module.all_comments.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.all_comments.adapter.AllCommentsDoctorAdapter;
import com.sanus.sanus.domain.doctor_module.all_comments.data.AllCommentsDoctor;
import com.sanus.sanus.domain.doctor_module.all_comments.presenter.AllCommentsPresenter;
import com.sanus.sanus.domain.doctor_module.all_comments.presenter.AllCommetnsPresenterImpl;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainActivityDoctor;

import java.util.List;

public class AllCommentsActivity extends AppCompatActivity implements AllCommetnsView{
    private AllCommentsPresenter presenter;
    RecyclerView recyclerView;
    private String idDoct;
    AllCommentsDoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);
        setUpVariable();
        setUpView();
        presenter.viewComents(idDoct);
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new AllCommetnsPresenterImpl(this);
        }
    }

    private void setUpView() {
        idDoct = getIntent().getStringExtra("idDoctor");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_comments);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivityDoctor.class);
                intent.putExtra("idDoctor", idDoct);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void setDataAdapter(List<AllCommentsDoctor> commentsDoctorList) {
        AllCommentsDoctorAdapter commentsDoctorAdapter = new AllCommentsDoctorAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

}
