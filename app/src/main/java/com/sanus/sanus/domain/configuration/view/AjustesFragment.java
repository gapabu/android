package com.sanus.sanus.domain.configuration.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sanus.sanus.utils.alert.CallbackAlert;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenterImpl;
import com.sanus.sanus.domain.login.view.LoginActivity;
import com.sanus.sanus.R;
import com.sanus.sanus.utils.alert.AlertUtils;


public class AjustesFragment extends Fragment implements AjustesView, CallbackAlert {

    public static String IDENTIFIER = "CONFIG_FRAGMENT";
    private AjustesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        setUpVariable();
        setUpView(view);
        
        return view;

    }

    private void setUpVariable() {

        if (presenter == null) {
            presenter = new AjustesPresenterImpl(this);
        }
    }

    private void setUpView(View view) {

        LinearLayout linearLayoutLogout = view.findViewById(R.id.logout);
        linearLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertLogout();
            }
        });

    }

    private void showAlertLogout() {
        AlertUtils alertUtils = new AlertUtils(this);
        alertUtils.configureAlert(getContext(), getString(R.string.logout_msg));
    }


    @Override
    public void acceptAlert() {
        presenter.logout();
    }

    @Override
    public void cancelAlert() {

    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
