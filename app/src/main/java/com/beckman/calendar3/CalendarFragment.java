package com.beckman.calendar3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.andexert.calendarlistview.DatePickerController;
import com.andexert.calendarlistview.DayPickerView;
import com.andexert.calendarlistview.SimpleMonthAdapter;

import java.util.Calendar;
import java.util.TimeZone;

import static com.andexert.calendarlistview.DayPickerView.DATE_TARGET_AFTER;
import static com.andexert.calendarlistview.DayPickerView.DATE_TARGET_BEFORE;
import static com.andexert.calendarlistview.DayPickerView.DATE_TARGET_TODAY;

public class CalendarFragment extends DialogFragment {
    DayPickerView mDayPickerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDayPickerView = (DayPickerView) view.findViewById(R.id.pickerView);
        mDayPickerView.setController(new CalendarController());
        int monthCount = mDayPickerView.getAdapter().getItemCount();
        mDayPickerView.smoothScrollToPosition(monthCount);
        return view;
    }

    private void defineSelectTimeRange(){
        // if want to define selected day range , use this
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, -2);
        calendar.add(Calendar.DAY_OF_MONTH, -3);
//        mDayPickerView.setNextDayEnabled(DATE_TARGET_TODAY);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.add(Calendar.DAY_OF_MONTH,0);
        mDayPickerView.setDateRange(calendar.getTimeInMillis(), calendar2.getTimeInMillis());
    }

    class CalendarController implements DatePickerController {
        @Override
        public int getMaxYear() {
            return Calendar.getInstance().get(Calendar.YEAR);
        }

        @Override
        public int getMinYear() {
            return 2015;
        }

        @Override
        public void onDayOfMonthSelected(int year, int month, int day) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(year).append("-");
            if (month < 9) {
                stringBuilder.append("0").append(month + 1).append("-");
            } else {
                stringBuilder.append(month).append(1).append("-");
            }
            if (day < 10) {
                stringBuilder.append("0").append(day);
            } else {
                stringBuilder.append(day);
            }
            Log.i("123", "--- " + stringBuilder.toString());
        }

        @Override
        public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
            Log.i("123", "--- " + selectedDays.getFirst().toString() + selectedDays.getLast().toString());
            mDayPickerView.getSelectedDays().setFirst(mDayPickerView.getSelectedDays().getLast());
        }
    }
}
