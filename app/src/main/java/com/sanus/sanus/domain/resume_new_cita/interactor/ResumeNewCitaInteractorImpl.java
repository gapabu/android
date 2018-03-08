package com.sanus.sanus.domain.resume_new_cita.interactor;


import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;

public class ResumeNewCitaInteractorImpl implements ResumeNewCitaInteractor{
    private ResumeNewCitaPresenter presenter;

    public ResumeNewCitaInteractorImpl (ResumeNewCitaPresenter presenter){
        this.presenter = presenter;
    }

}
