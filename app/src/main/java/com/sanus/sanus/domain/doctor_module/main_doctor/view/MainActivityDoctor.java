package com.sanus.sanus.domain.doctor_module.main_doctor.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.configuration.view.AjustesFragment;
import com.sanus.sanus.domain.doctor_module.chat.view.ChatDoctorFragment;
import com.sanus.sanus.domain.doctor_module.citas.view.CitasDoctorFragment;
import com.sanus.sanus.domain.doctor_module.main_doctor.presenter.MainDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.main_doctor.presenter.MainDoctorPresenterImpl;
import com.sanus.sanus.domain.doctor_module.profile.view.ProfileFragment;

public class MainActivityDoctor extends AppCompatActivity implements MainDoctorView {

    private BottomNavigationViewEx navigation;
    private MainDoctorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);
        setUpVariable();
        setUpView();
        showProfileFragment();
    }

    private void setUpVariable(){
        if (presenter == null){
            presenter = new MainDoctorPresenterImpl(this);
        }
    }
    private void setUpView(){
        navigation = findViewById(R.id.navigation);
        navigation.enableAnimation(true);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);

        setUpBottomNavigationBar();
    }

    private void setUpBottomNavigationBar() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_doctor:
                        showProfileFragment();
                        return true;
                    case R.id.navigation_citas:
                        showCitasFragment();
                        return true;
                    case R.id.navigation_chat:
                        showChatFragment();
                        return true;
                    case R.id.navigation_ajustes:
                        showConfigFragment();
                        return true;
                }
                return true;
            }
        });
    }

    private void showProfileFragment() {

        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.IDENTIFIER);
        if(profileFragment == null){
            profileFragment = new ProfileFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, profileFragment, ProfileFragment.IDENTIFIER)
                .commit();

    }

    private void showCitasFragment() {
        CitasDoctorFragment citasFragment = (CitasDoctorFragment) getSupportFragmentManager().findFragmentByTag(CitasDoctorFragment.IDENTIFIER);
        if(citasFragment == null){
            citasFragment = new CitasDoctorFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, citasFragment, CitasDoctorFragment.IDENTIFIER)
                .commit();

        /*CitasFragment citasFragment = (CitasFragment) getSupportFragmentManager().findFragmentByTag(CitasFragment.IDENTIFIER);
        if(citasFragment == null){
            citasFragment = new CitasFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, citasFragment, CitasFragment.IDENTIFIER)
                .commit();*/

    }

    private void showChatFragment() {
        ChatDoctorFragment chatFragment = (ChatDoctorFragment) getSupportFragmentManager().findFragmentByTag(ChatDoctorFragment.IDENTIFIER);
        if(chatFragment == null){
            chatFragment = new ChatDoctorFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, chatFragment, ChatDoctorFragment.IDENTIFIER)
                .commit();

    }
    private void showConfigFragment() {
        AjustesFragment ajustesFragment = (AjustesFragment) getSupportFragmentManager().findFragmentByTag(AjustesFragment.IDENTIFIER);
        if(ajustesFragment == null){
            ajustesFragment = new AjustesFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, ajustesFragment, AjustesFragment.IDENTIFIER)
                .commit();
    }
}
