package com.sanus.sanus.domain.splash.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.login.view.LoginActivity;
import com.sanus.sanus.domain.splash.presenter.SplashPresenter;
import com.sanus.sanus.domain.splash.presenter.SplashPresenterImpl;

public class SplashActivity extends AppCompatActivity implements SplasView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUpVariable();

        presenter.init();
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new SplashPresenterImpl(this);
        }
    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
