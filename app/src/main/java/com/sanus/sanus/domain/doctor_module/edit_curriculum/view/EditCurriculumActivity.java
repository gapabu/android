package com.sanus.sanus.domain.doctor_module.edit_curriculum.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenter;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenterImpl;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainActivityDoctor;

public class EditCurriculumActivity extends AppCompatActivity implements EditCurriculumView{

    private EditCurriculumPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_curriculum);
        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new EditCurriculumPresenterImpl(this);
        }
    }

    private void setUpView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, MainActivityDoctor.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                break;
        }
        return true;
    }
}
