package com.sanus.sanus.domain.doctor_module.profile.presenter;

import android.content.Context;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sanus.sanus.domain.doctor_module.profile.interactor.ProfileDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.profile.interactor.ProfileDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.profile.view.ProfileFragmentView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDoctorPresenterImpl implements ProfileDoctorPresenter {
    private ProfileFragmentView view;
    private ProfileDoctorInteractor interactor;

    public ProfileDoctorPresenterImpl(ProfileFragmentView view){
        this.view = view;
        interactor = new ProfileDoctorInteractorImpl(this);
    }


    @Override
    public void init(String idDoct, RatingBar ratingBar, TextView especialidad, TextView cedula, TextView cv) {
        interactor.init(idDoct, ratingBar, especialidad, cedula, cv);
    }

    @Override
    public void showImage(String idImage, Context context, CircleImageView image) {
        interactor.showImage(idImage, context, image);
    }
}
