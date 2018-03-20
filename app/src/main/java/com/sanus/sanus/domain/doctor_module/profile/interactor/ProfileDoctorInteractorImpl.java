package com.sanus.sanus.domain.doctor_module.profile.interactor;

import com.sanus.sanus.domain.doctor_module.profile.presenter.ProfileDoctorPresenter;

public class ProfileDoctorInteractorImpl implements ProfileDoctorInteractor {
    private ProfileDoctorPresenter presenter;

    public ProfileDoctorInteractorImpl(ProfileDoctorPresenter presenter){this.presenter = presenter;}
}
