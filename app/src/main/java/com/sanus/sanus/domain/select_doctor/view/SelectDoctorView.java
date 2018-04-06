package com.sanus.sanus.domain.select_doctor.view;


import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;

import java.util.List;

public interface SelectDoctorView {
    void setDataAdapter(List<SelectDoctor> commentsDoctorList);
    void goSelectDay(String value);
    void next();
    void previous();
}
