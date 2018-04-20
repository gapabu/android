package com.sanus.sanus.domain.doctor_module.edit_curriculum.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenter;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenterImpl;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainActivityDoctor;

public class EditCurriculumActivity extends AppCompatActivity implements EditCurriculumView{
    private Button save;
    private EditText curriculum;
    private EditCurriculumPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_curriculum);
        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new EditCurriculumPresenterImpl(this);
        }
    }

    private void setUpView() {

        presenter.getDataCurriculum();
        save = findViewById(R.id.btnGuardar);
        curriculum = findViewById(R.id.editCV);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");

        curriculum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                presenter.validateButtonEnable();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveCurriculum();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                goMain();
                break;
        }
        return true;
    }

    @Override
    public String getCurriculum() {
        return curriculum.getText().toString().trim();
    }

    @Override
    public String setCurriculum(String value) {
        curriculum.setText(value);
        return String.valueOf(curriculum);
    }

    @Override
    public void enableButton() {
        save.setEnabled(true);
    }

    @Override
    public void disableButton() {
        save.setEnabled(false);
    }

    @Override
    public void goMain() {
        startActivity(new Intent(this, MainActivityDoctor.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void alertSuccessUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.app_name);
        builder.setCancelable(false);
        builder.setMessage(R.string.update_curriculum);

        builder.setPositiveButton(R.string.button_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               goMain();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
