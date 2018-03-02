package com.sanus.sanus.domain.search.view;

import com.sanus.sanus.domain.search.data.BusquedaDoctor;

import java.util.List;

public interface SearchView {

    void setDataAdapter(List<BusquedaDoctor> busquedaDoctors);
}
