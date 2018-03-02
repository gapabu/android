package com.sanus.sanus.domain.curriculum.interactor;

import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;

public class CurriculumInteractorImpl implements CurriculumInteractor{
    private CurriculumPresenter presenter;

    public CurriculumInteractorImpl(CurriculumPresenter presenter) {
        this.presenter = presenter;
    }
}
