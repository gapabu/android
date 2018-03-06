package com.sanus.sanus.domain.curriculum.presenter;

import com.sanus.sanus.domain.curriculum.interactor.CurriculumInteractor;
import com.sanus.sanus.domain.curriculum.interactor.CurriculumInteractorImpl;
import com.sanus.sanus.domain.curriculum.view.CurriculumView;

public class CurriculumPresenterImpl implements CurriculumPresenter {
    private CurriculumView view;
    private CurriculumInteractor interactor;

    public CurriculumPresenterImpl(CurriculumView view) {
        this.view = view;
        interactor = new CurriculumInteractorImpl(this);
    }

    @Override
    public void init() {
        interactor.init();

    }

}
