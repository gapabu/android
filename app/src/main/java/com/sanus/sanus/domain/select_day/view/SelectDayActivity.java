package com.sanus.sanus.domain.select_day.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenterImpl;


public class SelectDayActivity extends AppCompatActivity implements SelectDayView {

    private SelectDayPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_day);
        setUpVariable();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectDayPresenterImpl(this);
        }
    }
}
