package com.sanus.sanus.domain.doctor_module.profile.presenter;

import com.sanus.sanus.domain.doctor_module.profile.interactor.ProfileDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.profile.interactor.ProfileDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.profile.view.ProfileFragmentView;

public class ProfileDoctorPresenterImpl implements ProfileDoctorPresenter {
    private ProfileFragmentView view;
    private ProfileDoctorInteractor interactor;

    public ProfileDoctorPresenterImpl(ProfileFragmentView view){
        this.view = view;
        interactor = new ProfileDoctorInteractorImpl(this);
    }
}
