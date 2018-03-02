package com.sanus.sanus.domain.citas.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.domain.citas.adapter.CitasAdapter;
import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.citas.presenter.CitasPresenter;
import com.sanus.sanus.domain.citas.presenter.CitasPresenterImpl;

import java.util.List;

public class CitasFragment extends Fragment implements CitasView {

    private CitasPresenter presenter;
    private RecyclerView recyclerView;
    public static String IDENTIFIER = "CITAS_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citas, container, false);

        setUpVariable();
        setUpView(view);
        presenter.init();

        return view;
    }

    private void setUpVariable() {

        if (presenter == null) {
            presenter = new CitasPresenterImpl(this);
        }

    }

    private void setUpView(View view) {

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }


    @Override
    public void setDataAdapter(List<Citas> busquedaDoctors) {

        CitasAdapter citasAdapter = new CitasAdapter(getContext(), busquedaDoctors);
        recyclerView.setAdapter(citasAdapter);
        citasAdapter.notifyDataSetChanged();

    }
}