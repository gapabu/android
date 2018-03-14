package com.sanus.sanus.domain.search.presenter;


import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.domain.search.interactor.SearchInteractor;
import com.sanus.sanus.domain.search.interactor.SearchInteractorImpl;
import com.sanus.sanus.domain.search.view.SearchView;

import java.util.List;

public class SearchPresenterImpl implements SearchPresenter {
    private SearchView view;
    private SearchInteractor interactor;

    public SearchPresenterImpl(SearchView view) {
        this.view = view;
        interactor = new SearchInteractorImpl(this);
    }

    @Override
    public void init() {
        interactor.init();
    }

    @Override
    public void buscador(String texto) {
        interactor.buscador(texto);
    }


    @Override
    public void setDataAdapter(List<BusquedaDoctor> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }
}
