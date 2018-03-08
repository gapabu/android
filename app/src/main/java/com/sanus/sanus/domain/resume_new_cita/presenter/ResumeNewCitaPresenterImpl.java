package com.sanus.sanus.domain.resume_new_cita.presenter;


import com.sanus.sanus.domain.resume_new_cita.interactor.ResumeNewCitaInteractor;
import com.sanus.sanus.domain.resume_new_cita.interactor.ResumeNewCitaInteractorImpl;
import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaView;

public class ResumeNewCitaPresenterImpl implements ResumeNewCitaPresenter{
    private ResumeNewCitaView view;
    private ResumeNewCitaInteractor interactor;

    public ResumeNewCitaPresenterImpl(ResumeNewCitaView view){
        this.view = view;
        interactor = new ResumeNewCitaInteractorImpl(this);
    }
}
