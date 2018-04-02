package com.sanus.sanus.domain.search.view;

import android.net.Uri;

import com.sanus.sanus.domain.search.data.BusquedaDoctor;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SearchView {

    void setDataAdapter(List<BusquedaDoctor> busquedaDoctors);
    void showPhoto(Uri photo, CircleImageView avatar);
}
