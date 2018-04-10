package com.sanus.sanus.domain.select_day.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenterImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;
import com.sanus.sanus.domain.select_hour.view.SelectHourActivity;

import java.util.Calendar;

public class SelectDayActivity extends AppCompatActivity implements SelectDayView, CalendarView.OnDateChangeListener {
    private String TAG = this.getClass().getSimpleName();
    private SelectDayPresenter presenter;
    FloatingActionButton skip, next;
    CalendarView calendarView;
    String idHospital, idDoctor;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_day);
        setUpVariable();
        setUpView();
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new SelectDayPresenterImpl(this);
        }
    }

    private void setUpView() {
        idHospital = getIntent().getStringExtra("idHospital");
        idDoctor = getIntent().getStringExtra("idDoctor");
        Log.d(TAG, "idHospital: " + idHospital + " " + "idDoctor: " + idDoctor);

        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(this);

        disableButton();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               previous();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        month++;
        fecha = dayOfMonth + "/" + month + "/" + year;

        Log.d(TAG, "fecha: " + fecha);


        Calendar c = Calendar.getInstance();
        c.set(year,month,dayOfMonth);// vairables int

        month = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        Log.d(TAG, "total: " + month);
        dayOfMonth = c.get(Calendar.DAY_OF_WEEK);

        if (month == 30){
            if(dayOfMonth==Calendar.SUNDAY){Log.d(TAG, "jueves " + dayOfMonth);}
            else if(dayOfMonth==Calendar.MONDAY){Log.d(TAG, "viernes " + dayOfMonth);}
            else if (dayOfMonth==Calendar.TUESDAY){Log.d(TAG, "sabado " + dayOfMonth);}
            else if (dayOfMonth==Calendar.WEDNESDAY){Log.d(TAG, "domingo " + dayOfMonth);}
            else if (dayOfMonth==Calendar.THURSDAY){Log.d(TAG, "lunes " + dayOfMonth);}
            else if (dayOfMonth==Calendar.FRIDAY){Log.d(TAG, "martes " + dayOfMonth);}
            else{Log.d(TAG, "miercoles " + dayOfMonth);}
        }if (month == 31){
            if(dayOfMonth==Calendar.SUNDAY){Log.d(TAG, "viernes " + dayOfMonth);}
            else if(dayOfMonth==Calendar.MONDAY){Log.d(TAG, "sabado " + dayOfMonth);}
            else if (dayOfMonth==Calendar.TUESDAY){Log.d(TAG, "domingo " + dayOfMonth);}
            else if (dayOfMonth==Calendar.WEDNESDAY){Log.d(TAG, "lunes " + dayOfMonth);}
            else if (dayOfMonth==Calendar.THURSDAY){Log.d(TAG, "martes " + dayOfMonth);}
            else if (dayOfMonth==Calendar.FRIDAY){Log.d(TAG, "miercoles " + dayOfMonth);}
            else{Log.d(TAG, "jueves " + dayOfMonth);}
        }

        enableButton();
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
    public void next() {
        Intent intent = new Intent(SelectDayActivity.this, SelectHourActivity.class);
        intent.putExtra("idDoctor", idDoctor);
        intent.putExtra("idHospital", idHospital);
        intent.putExtra("fecha", fecha);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }

    @Override
    public void previous() {
        Intent intent = new Intent(SelectDayActivity.this, SelectDoctorActivity.class);
        intent.putExtra("idHospital", idHospital);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}
