package com.sanus.sanus.domain.hospital.presenter;

import com.sanus.sanus.domain.hospital.view.HospitalView;

public interface HospitalPresenter extends HospitalView {
    void viewHospital();
    void buscador(String texto);
}
