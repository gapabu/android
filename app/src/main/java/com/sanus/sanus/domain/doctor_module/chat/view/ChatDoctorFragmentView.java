package com.sanus.sanus.domain.doctor_module.chat.view;

import com.sanus.sanus.domain.doctor_module.chat.data.ContactDoctor;

import java.util.List;

public interface ChatDoctorFragmentView {
    void setDataAdapter(List<ContactDoctor> busquedaDoctors);

}
