package com.sanus.sanus.domain.select_hour.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenterImpl;

public class SelectHourActivity extends AppCompatActivity implements SelectHourView{
    private SelectHourPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_day);
        setUpVariable();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectHourPresenterImpl(this);
        }
    }
}
