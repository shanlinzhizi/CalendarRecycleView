package com.beckman.calendar3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView calendar = (TextView) findViewById(R.id.tv_main_calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarFragment calendarFragment = new CalendarFragment();
                calendarFragment.show(getSupportFragmentManager(), "CalendarDialog");
            }
        });
    }
}
