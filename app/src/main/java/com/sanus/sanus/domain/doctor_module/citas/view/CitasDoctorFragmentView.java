package com.sanus.sanus.domain.doctor_module.citas.view;

import android.net.Uri;

import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CitasDoctorFragmentView {
    void setDataAdapter(List<Appointment> busquedaDoctors);
    void showPhoto(Uri photo, CircleImageView avatar);
}
