package com.sanus.sanus.domain.configuration.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.sanus.sanus.domain.account.complete.view.CompleteRegisterActivity;
import com.sanus.sanus.domain.splash.view.SplashActivity;
import com.sanus.sanus.utils.alert.CallbackAlert;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenterImpl;
import com.sanus.sanus.R;
import com.sanus.sanus.utils.alert.AlertUtils;


import de.hdodenhof.circleimageview.CircleImageView;

public class AjustesFragment extends Fragment implements AjustesView, CallbackAlert {

    public static String IDENTIFIER = "CONFIG_FRAGMENT";
    private AjustesPresenter presenter;
    private TextView tvNombre;
    private CircleImageView setupImage;
    private Button activo, inactivo;
    private LinearLayout linearLayoutEdit;

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
        setupImage = view.findViewById(R.id.avatar);
        LinearLayout linearLayoutLogout = view.findViewById(R.id.logout);
        linearLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertLogout();
            }
        });

        linearLayoutEdit = view.findViewById(R.id.editProfile);

        linearLayoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CompleteRegisterActivity.class));
            }
        });

        activo = view.findViewById(R.id.btnActivo);
        inactivo = view.findViewById(R.id.btnInactivo);

        activo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActive();
            }
        });

        inactivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectInactive();
            }
        });

        FirebaseAuth.AuthStateListener mAutthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    goSplash();
                }
            }
        };

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
    public void showName(String name) {
        tvNombre.setText(name);
    }

    @Override
    public void showData(String name, String email) {
        tvNombre.setText(email);
    }

    @Override
    public void showPhoto(Uri uri) {
        //GlideApp.with(this).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
        //Picasso.with(getContext()).load(uri.toString()).placeholder(R.drawable.user).into(setupImage);
    }

    @Override
    public void selectActive() {
        activo.setBackgroundColor(getResources().getColor(R.color.black));
        activo.setTextColor(getResources().getColor(R.color.white));
        inactivo.setBackgroundColor(getResources().getColor(R.color.white));
        inactivo.setTextColor(getResources().getColor(R.color.black));
        presenter.onClickActive("1");
    }

    @Override
    public void selectInactive() {
        inactivo.setBackgroundColor(getResources().getColor(R.color.black));
        inactivo.setTextColor(getResources().getColor(R.color.white));
        activo.setBackgroundColor(getResources().getColor(R.color.white));
        activo.setTextColor(getResources().getColor(R.color.black));
        presenter.onClickActive("0");
    }
}
