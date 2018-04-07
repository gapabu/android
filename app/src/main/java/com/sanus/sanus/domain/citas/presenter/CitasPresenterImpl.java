package com.sanus.sanus.domain.citas.presenter;

import android.net.Uri;

import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.domain.citas.interactor.CitasInteractor;
import com.sanus.sanus.domain.citas.interactor.CitasInteractorImpl;
import com.sanus.sanus.domain.citas.view.CitasView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitasPresenterImpl implements  CitasPresenter {
    private CitasView view;
    private CitasInteractor interactor;

    public CitasPresenterImpl(CitasView view) {
        this.view = view;
        interactor = new CitasInteractorImpl(this);

    }

    @Override
    public void init() {
        interactor.init();
    }

    @Override
    public void showImage(String idImage, CircleImageView image) {
        interactor.showImage(idImage, image);
    }

    @Override
    public void setDataAdapter(List<Citas> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }

    @Override
    public void showPhoto(Uri photo, CircleImageView avatar) {
        view.showPhoto(photo, avatar);
    }
}
