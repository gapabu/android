package com.sanus.sanus.domain.citas.view;

import android.net.Uri;

import com.sanus.sanus.domain.citas.data.Citas;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CitasView {

    void setDataAdapter(List<Citas> busquedaDoctors);
    void showPhoto(Uri photo, CircleImageView avatar);
}
