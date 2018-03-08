package com.sanus.sanus.domain.resume_new_cita.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenterImpl;

public class ResumeNewCitaActivity extends AppCompatActivity implements ResumeNewCitaView{
    private ResumeNewCitaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_new_cita);

        setUpVariable();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new ResumeNewCitaPresenterImpl(this);
        }
    }
}
