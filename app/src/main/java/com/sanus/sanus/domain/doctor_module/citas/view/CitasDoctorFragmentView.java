package com.sanus.sanus.domain.doctor_module.citas.view;

import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;

import java.util.List;

public interface CitasDoctorFragmentView {
    void setDataAdapter(List<Appointment> busquedaDoctors);
}
