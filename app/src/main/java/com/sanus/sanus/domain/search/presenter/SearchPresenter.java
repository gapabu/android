package com.sanus.sanus.domain.search.presenter;


import com.sanus.sanus.domain.search.view.SearchView;

public interface SearchPresenter extends SearchView {
    void init();
    void buscador(String texto);
}
