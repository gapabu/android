package com.sanus.sanus.domain.splash.interactor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sanus.sanus.domain.splash.presenter.SplashPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class SplashInteractorImpl implements SplashInteractor{
    private SplashPresenter presenter;
    private static final long SPLASH_SCREEN_DELETE = 1000;

    public SplashInteractorImpl(SplashPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    presenter.goMain();
                    return;
                }
                presenter.goLogin();

            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, SPLASH_SCREEN_DELETE);
    }
}
