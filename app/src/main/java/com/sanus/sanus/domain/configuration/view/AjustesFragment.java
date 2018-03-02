package com.sanus.sanus.domain.configuration.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.sanus.sanus.domain.splash.view.SplashActivity;
import com.sanus.sanus.utils.alert.CallbackAlert;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenterImpl;
import com.sanus.sanus.R;
import com.sanus.sanus.utils.alert.AlertUtils;

import com.sanus.sanus.utils.glide.GlideApp;

public class AjustesFragment extends Fragment implements AjustesView, CallbackAlert {

    public static String IDENTIFIER = "CONFIG_FRAGMENT";
    private AjustesPresenter presenter;
    private TextView tvNombre;
    private ImageView imgAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        setUpVariable();
        setUpView(view);
        
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void setUpVariable() {

        if (presenter == null) {
            presenter = new AjustesPresenterImpl(this);
        }
    }

    private void setUpView(View view) {

        tvNombre = view.findViewById(R.id.tvNombre);
        imgAvatar = view.findViewById(R.id.imgAvatar);

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
    public void goSplash() {
        Intent intent = new Intent(getActivity(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        if(getActivity() != null){
            getActivity().finish();
        }
    }

    @Override
    public void showData(String name, String email) {
        tvNombre.setText(email);
    }

    @Override
    public void showPhoto(String photo) {
        GlideApp.with(this).load(photo).transform(new RoundedCorners(500)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgAvatar);
    }
}
