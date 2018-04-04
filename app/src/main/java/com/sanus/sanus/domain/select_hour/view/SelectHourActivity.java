package com.sanus.sanus.domain.select_hour.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaActivity;
import com.sanus.sanus.domain.select_day.view.SelectDayActivity;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenterImpl;

public class SelectHourActivity extends AppCompatActivity implements SelectHourView{
    private SelectHourPresenter presenter;
    private FloatingActionButton skip, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_hour);
        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectHourPresenterImpl(this);
        }
    }

    private void setUpView() {
        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectHourActivity.this, SelectDayActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectHourActivity.this, ResumeNewCitaActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });
    }
}
