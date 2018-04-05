package com.sanus.sanus.domain.hospital.view;

import com.sanus.sanus.domain.hospital.data.Hospital;

import java.util.List;

public interface HospitalView {
    void setDataAdapter(List<Hospital> commentsDoctorList);
    void enableButton();
    void disableButton();
    void selectDoctor(String value);
}
