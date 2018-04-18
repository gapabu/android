package com.sanus.sanus.domain.select_hour.view;

import com.sanus.sanus.domain.select_hour.data.SelectHour;

import java.util.List;

public interface SelectHourView {
    void enableButton();
    void disableButton();
    void next(String value);
    void previous();
    void setDataAdapter(List<SelectHour> busquedaDoctors);
    void setDate(String idHora, String idFecha);
}
