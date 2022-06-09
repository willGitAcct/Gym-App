package com.example.surgegym;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;


public class ClassesFragment extends Fragment {
    CalendarView calendarView;
    Calendar calendar;

    TextView classes1,classes2,classes3;
    Button viewClasses;

    int day;

    //to display the classes which are available during the week
    //the maintainer of the app need only change the strings here every few months when the schedules
    //change
    public void showClass(int day){

        //for whatever reason the calendar displays a day behind.
        //for example it will display Wednesday when you click thursday
        //so adjust schedule accordingly
        if(day==Calendar.MONDAY) {
            classes1.setText("14:00 - 15:00 : Live Zumba Dance!");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("18:00 - 19:00 : Cardio Kickboxing");

        }else if (day==Calendar.TUESDAY){
            classes1.setText("15:00 - 16:00 : Cardio Kickboxing");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("Tues");
        }else if (day==Calendar.WEDNESDAY){
            classes1.setText("14:00 - 15:00 : Group Jogging");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("weds");
        }else if (day==Calendar.THURSDAY){
            classes1.setText("13:00 - 14:00 : Lunctime Layups");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("Thursday");
        }else if (day==Calendar.FRIDAY){
            classes1.setText("09:00 - 10:30 : Breakfast Burpees!");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("Fri");
        }else if (day==Calendar.SATURDAY){
            classes1.setText("09:00 - 10:30 : Breakfast Burpees!");
            classes2.setText("16:00 - 17:00 : Group Cycling Jam");
            classes3.setText("Sat");
        }else if (day==Calendar.SUNDAY){
            classes1.setText("10:00 - 12:00 : Live Zumba Dance!");
            classes2.setText("");
            classes3.setText("");
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        classes1 = (TextView) view.findViewById(R.id.class1);
        classes2 = (TextView) view.findViewById(R.id.class2);
        classes3 = (TextView) view.findViewById(R.id.class3);
        viewClasses = (Button) view.findViewById(R.id.checkClasses);

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendar = calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        long endOfMonth = calendar.getTimeInMillis();

        calendar = calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        long startOfMonth = calendar.getTimeInMillis();
        calendarView.setMaxDate(endOfMonth);
        calendarView.setMinDate(startOfMonth);

        day = calendar.get(Calendar.DAY_OF_WEEK);
        //calendar.setFirstDayOfWeek(day);




        viewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showClass(day);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //day = calendar.get(Calendar.DAY_OF_WEEK);
                //showClass(i2);
                //showClass(i1);
                //classes3.setText("Testing if anything happens");
               // calendar = calendar.getInstance();

                //calendar = Calendar.getInstance();

                //showClass(i2); //dates mixed up, only works for the first week
                //showClass(i);
                LocalDate localDate = LocalDate.of(i, i1+1, i2);
                DayOfWeek dayOfWeek
                        = DayOfWeek.from(localDate);
                int val = dayOfWeek.getValue();
                showClass(val);
            }
        });


        //no code after here!
        return view;
    }
}