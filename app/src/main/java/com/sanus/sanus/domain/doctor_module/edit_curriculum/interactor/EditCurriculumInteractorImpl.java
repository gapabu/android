package com.sanus.sanus.domain.doctor_module.edit_curriculum.interactor;

import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenter;

public class EditCurriculumInteractorImpl implements EditCurriculumInteractor {
    private EditCurriculumPresenter presenter;

    public EditCurriculumInteractorImpl(EditCurriculumPresenter presenter){this.presenter = presenter;}
}
