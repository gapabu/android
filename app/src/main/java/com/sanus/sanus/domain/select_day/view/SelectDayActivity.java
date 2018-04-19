package com.sanus.sanus.domain.select_day.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenterImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorActivity;
import com.sanus.sanus.domain.select_hour.view.SelectHourActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectDayActivity extends AppCompatActivity implements SelectDayView, OnDayClickListener {
    private String TAG = this.getClass().getSimpleName();
    private SelectDayPresenter presenter;
    private String idHospital, idDoctor,fecha;
    private String monthYear = null;
    private String dayMont = null;
    private FloatingActionButton next;
    String idDocument;
    FloatingActionButton previous;
    CalendarView calendarView;

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
        disableButton();

        calendarView = findViewById(R.id.calendarView);
        calendarView.showCurrentMonthPage();
        calendarView.setMinimumDate(Calendar.getInstance());
        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(this);

        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.circle_green));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 5);
        events.add(new EventDay(calendar2, R.drawable.circle_red));
        calendarView.setEvents(events);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.deleteAppointment(idDocument);
                previous();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addAppointment(idHospital, idDoctor, fecha);
            }
        });
    }

    @SuppressLint("WrongConstant")
    private List<Calendar> getDisabledDays() {
        //DIA
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_WEEK, 1);

        //semana
        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);

        //dias
        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 8);

        //dias
        Calendar four = DateUtils.getCalendar();
        four.add(Calendar.DATE, 2);

        //mes
        Calendar five = DateUtils.getCalendar();
        five.add(Calendar.MONTH, 1);

        //semana
        Calendar six = DateUtils.getCalendar();
        six.add(Calendar.WEEK_OF_MONTH, 1);

        //semana
        Calendar seven = DateUtils.getCalendar();
        seven.add(Calendar.MAY, 1);

        //dias
        Calendar eight = DateUtils.getCalendar();
        eight.add(Calendar.SATURDAY, 5);

        //mes
        Calendar nine = DateUtils.getCalendar();
        nine.add(Calendar.LONG, 1);

        Calendar ten = DateUtils.getCalendar();//nada
        ten.add(Calendar.ALL_STYLES, 1);

        List<Calendar> calendars = new ArrayList<>();
        //calendars.add(firstDisabled);
        //calendars.add(secondDisabled);
        //calendars.add(thirdDisabled);
        //calendars.add(four);
        calendars.add(ten);

        return calendars;
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
