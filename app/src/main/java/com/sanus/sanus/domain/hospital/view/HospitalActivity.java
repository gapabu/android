package com.sanus.sanus.domain.hospital.view;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.adapter.HospitalAdapter;
import com.sanus.sanus.domain.hospital.data.Hospital;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenter;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenterImpl;
import com.sanus.sanus.domain.main.view.MainActivity;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;
import com.sanus.sanus.utils.alert.AlertUtils;
import com.sanus.sanus.utils.alert.CallbackAlert;

import java.util.List;

public class HospitalActivity extends AppCompatActivity implements HospitalView, CallbackAlert {
    private HospitalPresenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton next;
    HospitalAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_hospital);
        setUpVariable();
        setUpView();
        presenter.viewHospital();
    }

    private void setUpVariable() {
        if(presenter == null){
            presenter = new HospitalPresenterImpl(this);
        }
    }

    private void setUpView() {

        //final SharedPreferences preferences = getSharedPreferences("hospital", Context.MODE_PRIVATE);

        next = findViewById(R.id.btn_next);
        FloatingActionButton close = findViewById(R.id.btn_close);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        EditText edbuscador = findViewById(R.id.edbuscador);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HospitalActivity.this, "id: " + preferences.getString("idHospital", "error"), Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(HospitalActivity.this, SelectDoctorActivity.class);
                intent.putExtra("id", idUs);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();*/


            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertCancel();
            }
        });

        edbuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                presenter.buscador(s.toString());
            }
        });
    }


    @Override
    public void setDataAdapter(List<Hospital> commentsDoctorList) {
        HospitalAdapter commentsDoctorAdapter = new HospitalAdapter(getApplicationContext(), commentsDoctorList, presenter);
        recyclerView.setAdapter(commentsDoctorAdapter);
        commentsDoctorAdapter.notifyDataSetChanged();
    }

    @Override
    public void enableButton() {
        next.setEnabled(true);
        next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public void disableButton() {
        next.setEnabled(false);

    }

    @Override
    public void goSelectDoctor(String value ) {
        Intent intent = new Intent(this, SelectDoctorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("idHospital", value);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);

        /*SharedPreferences preferences = getSharedPreferences("hospital", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idHospital", value);
        editor.clear();*/
    }

    @Override
    public void closeNewCita() {
        Intent intent = new Intent(HospitalActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void acceptAlert() {
        closeNewCita();
    }

    @Override
    public void cancelAlert() {

    }

    private void showAlertCancel() {
        AlertUtils alertUtils = new AlertUtils(this);
        alertUtils.configureAlert(this, getString(R.string.salir_citas));
    }
}
