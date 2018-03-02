package com.sanus.sanus.domain.foro.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.foro.presenter.ForoPresenter;
import com.sanus.sanus.domain.foro.presenter.ForoPresenterImpl;

public class ForoFragment extends Fragment implements ForoView {

    public static String IDENTIFIER = "FORO_FRAGMENT";
    private ForoPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foro, container, false);
        setUpVariable();

        return view;
    }

    private void setUpVariable() {
        if (presenter == null) {
            presenter = new ForoPresenterImpl(this);
        }
    }
}