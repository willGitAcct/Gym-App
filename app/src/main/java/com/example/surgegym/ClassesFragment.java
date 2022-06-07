package com.example.surgegym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;


public class ClassesFragment extends Fragment {
    CalendarView calendarView;
    Calendar calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendar = calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        long endOfMonth = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        long startOfMonth = calendar.getTimeInMillis();
        calendarView.setMaxDate(endOfMonth);
        calendarView.setMinDate(startOfMonth);
        return view;
    }
}