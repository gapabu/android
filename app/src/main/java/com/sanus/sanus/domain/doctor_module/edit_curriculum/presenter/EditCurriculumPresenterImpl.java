package com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter;

import com.sanus.sanus.domain.doctor_module.edit_curriculum.interactor.EditCurriculumInteractor;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.interactor.EditCurriculumInteractorImpl;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.view.EditCurriculumView;

public class EditCurriculumPresenterImpl implements EditCurriculumPresenter {
    private EditCurriculumView view;
    private EditCurriculumInteractor interactor;

    public EditCurriculumPresenterImpl(EditCurriculumView view){
        this.view = view;
        interactor = new EditCurriculumInteractorImpl(this);
    }
}
