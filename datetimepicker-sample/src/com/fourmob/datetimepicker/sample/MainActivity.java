package com.fourmob.datetimepicker.sample;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.sleepbot.datetimepicker.time.TimePickerView;

import java.util.Calendar;

public class MainActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerView.OnTimeSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    public TextView tvHour;
    public TextView tvMinute;
    public Button btDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);
        btDialog = (Button) findViewById(R.id.btDialog);


        final Calendar calendar = Calendar.getInstance();
        final TimePickerView timePickerView = TimePickerView.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true, false, getResources().getColor(R.color.green));

        tvHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerView.setCurrentItemShowing(TimePickerView.HOUR_INDEX, true, false, true);
            }
        });

        tvMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerView.setCurrentItemShowing(TimePickerView.MINUTE_INDEX, true, false, true);
            }
        });


        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show(getSupportFragmentManager(), "whatever");
            }
        });


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flTimerContainer, timePickerView)
                .commit();

    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(MainActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHourSet(CharSequence hourOfDay, boolean advance) {
        tvHour.setText(hourOfDay);
        if (advance) System.out.println("hour released");
    }

    @Override
    public void onMinuteSet(CharSequence minute, boolean advance) {
        tvMinute.setText(minute);
        if (advance) System.out.println("minute released");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        Toast.makeText(MainActivity.this, "new time:" + hourOfDay + "-" + minute, Toast.LENGTH_LONG).show();
    }
}
