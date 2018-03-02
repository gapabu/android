package com.sanus.sanus.domain.curriculum.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenterImpl;


public class CurriculumActivity extends AppCompatActivity implements CurriculumView {

    private CurriculumPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        setUpVariable();
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new CurriculumPresenterImpl(this);
        }
    }
}
