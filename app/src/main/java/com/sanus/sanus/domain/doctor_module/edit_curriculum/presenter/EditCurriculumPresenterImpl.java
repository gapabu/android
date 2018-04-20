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

    @Override
    public String getCurriculum() {
        return view.getCurriculum();
    }

    @Override
    public String setCurriculum(String value) {
        return view.setCurriculum(value);
    }

    @Override
    public void enableButton() {
        view.enableButton();
    }

    @Override
    public void disableButton() {
        view.disableButton();
    }

    @Override
    public void goMain() {
        view.goMain();
    }

    @Override
    public void alertSuccessUpdate() {
        view.alertSuccessUpdate();
    }

    @Override
    public void validateButtonEnable() {
        interactor.validateButtonEnable();
    }

    @Override
    public void saveCurriculum() {
        interactor.saveCurriculum();
    }

    @Override
    public void getDataCurriculum() {
        interactor.getDataCurriculum();
    }
}
