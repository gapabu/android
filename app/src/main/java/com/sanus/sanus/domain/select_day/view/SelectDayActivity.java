package com.sanus.sanus.domain.select_day.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenterImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;
import com.sanus.sanus.domain.select_hour.view.SelectHourActivity;

import java.util.Calendar;

public class SelectDayActivity extends AppCompatActivity implements SelectDayView, OnDayClickListener {
    private String TAG = this.getClass().getSimpleName();
    private SelectDayPresenter presenter;
    private String idHospital, idDoctor, idDocument, fecha;
    private String monthYear = null;
    private String dayMont = null;
    private FloatingActionButton next;
    FloatingActionButton previous;
    com.applandeo.materialcalendarview.CalendarView calendarView;

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
        idDocument = getIntent().getStringExtra("idDocument");
        Log.d(TAG, "idHospital: " + idHospital + " " + "idDoctor: " + idDoctor + " idDocument " + idDocument);

        previous = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        calendarView = findViewById(R.id.calendarView);

       calendarView.setOnDayClickListener(this);
       calendarView.setMinimumDate(Calendar.getInstance());
       disableButton();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteAppointment(idDocument);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addAppointment(idHospital, idDoctor, fecha);
            }
        });
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
    public void next(String idDocument) {
        Intent intent = new Intent(SelectDayActivity.this, SelectHourActivity.class);
        intent.putExtra("idDoctor", idDoctor);
        intent.putExtra("idHospital", idHospital);
        intent.putExtra("fecha", fecha);
        intent.putExtra("dia", dayMont);
        intent.putExtra("idDocument", idDocument);
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

    @Override
    public void onDayClick(EventDay eventDay) {
        Calendar calendar = eventDay.getCalendar();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        getMonthOfYear(month);
        getDayWeek(day);

        fecha = dayOfMonth + " " + monthYear + " " + year;
        Log.d(TAG, " " + fecha);
        enableButton();
    }

    public void getMonthOfYear(Integer month){
        switch (month){
            case 0:
                monthYear = (getResources().getString(R.string.calendar_january));
                break;
            case 1:
                monthYear = (getResources().getString(R.string.calendar_february));
                break;
            case 2:
                monthYear = (getResources().getString(R.string.calendar_march));
                break;
            case 3:
                monthYear = (getResources().getString(R.string.calendar_april));
                break;
            case 4:
                monthYear = (getResources().getString(R.string.calendar_may));
                break;
            case 5:
                monthYear = (getResources().getString(R.string.calendar_june));
                break;
            case 6:
                monthYear = (getResources().getString(R.string.calendar_july));
                break;
            case 7:
                monthYear = (getResources().getString(R.string.calendar_august));
                break;
            case 8:
                monthYear = (getResources().getString(R.string.calendar_september));
                break;
            case 9:
                monthYear = (getResources().getString(R.string.calendar_october));
                break;
            case 10:
                monthYear = (getResources().getString(R.string.calendar_november));
                break;
            case 11:
                monthYear = (getResources().getString(R.string.calendar_december));
                break;
        }
    }
    public void getDayWeek(Integer day) {
        switch (day) {
            case 1:
                dayMont = (getResources().getString(R.string.calendar_sunday));
                break;
            case 2:
                dayMont = (getResources().getString(R.string.calendar_monday));
                break;
            case 3:
                dayMont = (getResources().getString(R.string.calendar_tuesday));
                break;
            case 4:
                dayMont = (getResources().getString(R.string.calendar_wednesday));
                break;
            case 5:
                dayMont = (getResources().getString(R.string.calendar_thursday));
                break;
            case 6:
                dayMont = (getResources().getString(R.string.calendar_friday));
                break;
            case 7:
                dayMont = (getResources().getString(R.string.calendar_saturday));
                break;
        }
    }
}
