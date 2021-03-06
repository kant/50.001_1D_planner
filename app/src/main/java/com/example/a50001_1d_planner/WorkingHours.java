package com.example.a50001_1d_planner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import distributeTimeSlotsPackage.AvailableDay;

public class WorkingHours extends AppCompatActivity {
    private String TAG = "WorkingHoursActivity";
    private WorkingHoursDAO workingHoursDAO;
    private TimePicker Mondaystart;
    private TimePicker Mondayend;
    private TimePicker Tuesdaystart;
    private TimePicker Tuesdayend;
    private TimePicker Wednesdaystart;
    private TimePicker Wednesdayend;
    private TimePicker Thursdaystart;
    private TimePicker Thursdayend;
    private TimePicker Fridaystart;
    private TimePicker Fridayend;
    private TimePicker Saturdaystart;
    private TimePicker Saturdayend;
    private TimePicker Sundaystart;
    private TimePicker Sundayend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hours);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_title);

        this.workingHoursDAO = new WorkingHoursDAO(this);
        final long userID = 0;

        this.Mondaystart = findViewById(R.id.Mondaystart);
        this.Mondaystart.setIs24HourView(true);
        setTimePickerInterval(Mondaystart);
        this.Mondayend = findViewById(R.id.Mondayend);
        this.Mondayend.setIs24HourView(true);
        setTimePickerInterval(Mondayend);

        this.Tuesdaystart = findViewById(R.id.Tuesdaystart);
        this.Tuesdaystart.setIs24HourView(true);
        setTimePickerInterval(Tuesdaystart);
        this.Tuesdayend = findViewById(R.id.Tuesdayend);
        this.Tuesdayend.setIs24HourView(true);
        setTimePickerInterval(Tuesdayend);

        this.Wednesdaystart = findViewById(R.id.Wednesdaystart);
        this.Wednesdaystart.setIs24HourView(true);
        setTimePickerInterval(Wednesdaystart);
        this.Wednesdayend = findViewById(R.id.Wednesdayend);
        this.Wednesdayend.setIs24HourView(true);
        setTimePickerInterval(Wednesdayend);


        this.Thursdaystart = findViewById(R.id.Thursdaystart);
        this.Thursdaystart.setIs24HourView(true);
        setTimePickerInterval(Thursdaystart);
        this.Thursdayend = findViewById(R.id.Thursdayend);
        this.Thursdayend.setIs24HourView(true);
        setTimePickerInterval(Thursdayend);


        this.Fridaystart = findViewById(R.id.Fridaystart);
        this.Fridaystart.setIs24HourView(true);
        setTimePickerInterval(Fridaystart);
        this.Fridayend = findViewById(R.id.Fridayend);
        this.Fridayend.setIs24HourView(true);
        setTimePickerInterval(Fridayend);

        this.Saturdaystart = findViewById(R.id.Saturdaystart);
        this.Saturdaystart.setIs24HourView(true);
        setTimePickerInterval(Saturdaystart);
        this.Saturdayend = findViewById(R.id.Saturdayend);
        this.Saturdayend.setIs24HourView(true);
        setTimePickerInterval(Saturdayend);


        this.Sundaystart = findViewById(R.id.Sundaystart);
        this.Sundaystart.setIs24HourView(true);
        setTimePickerInterval(Sundaystart);
        this.Sundayend = findViewById(R.id.Sundayend);
        this.Sundayend.setIs24HourView(true);
        setTimePickerInterval(Sundayend);

        //get the existing working hours, this is assuming one time slot only
        ArrayList<AvailableDay> availableDayArrayList = workingHoursDAO.getAllAvailableDays();
        if(availableDayArrayList.size()>0){
            for(AvailableDay availableDay:availableDayArrayList){
                TreeMap<Double,Double> availableTimes =  availableDay.getAvailableTimes();
                if(availableTimes.size()>0){
                    for(double key: availableTimes.keySet()){
                        int hour = (int)key;
                        getCorrespondingDayEditText(availableDay.getDay(),true).setCurrentHour(hour);
                        getCorrespondingDayEditText(availableDay.getDay(),true).setCurrentMinute((key-hour)==0? 0:1);

                        double endTime = availableTimes.get(key);
                        hour = (int)endTime;
                        getCorrespondingDayEditText(availableDay.getDay(),false).setCurrentHour(hour);
                        getCorrespondingDayEditText(availableDay.getDay(),false).setCurrentMinute((endTime-hour)==0? 0:1);
                    }
                }
            }
        }

        Button backToMenu = findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMenuIntent = new Intent(getApplicationContext(), Menu.class);
                startActivity(backToMenuIntent);
            }
        });


        Button saveWorkingHours = findViewById(R.id.saveWorkingHours);
        Log.d(TAG,"fdasfd");
        saveWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verifyWorkingHours())
                    return;
                workingHoursDAO.createWorkingHours(userID, Calendar.MONDAY, getFormattedWorkingHours(Mondaystart,Mondayend), getFormattedBreakHours(Mondaystart,Mondayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.TUESDAY, getFormattedWorkingHours(Tuesdaystart,Tuesdayend), getFormattedBreakHours(Tuesdaystart,Tuesdayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.WEDNESDAY, getFormattedWorkingHours(Wednesdaystart,Wednesdayend), getFormattedBreakHours(Wednesdaystart,Wednesdayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.THURSDAY, getFormattedWorkingHours(Thursdaystart,Thursdayend), getFormattedBreakHours(Thursdaystart,Thursdayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.FRIDAY, getFormattedWorkingHours(Fridaystart,Fridayend), getFormattedBreakHours(Fridaystart,Fridayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.SATURDAY, getFormattedWorkingHours(Saturdaystart,Saturdayend), getFormattedBreakHours(Saturdaystart,Saturdayend));
                workingHoursDAO.createWorkingHours(userID, Calendar.SUNDAY, getFormattedWorkingHours(Sundaystart,Sundayend), getFormattedBreakHours(Sundaystart,Sundayend));
                Intent saveWorkingHoursIntent = new Intent(getApplicationContext(), Menu.class);
                startActivity(saveWorkingHoursIntent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent backToMenuIntent = new Intent(getApplicationContext(), Menu.class);
        startActivity(backToMenuIntent);
    }

    //break times should be formatted as "13.5,15.5" or ""
    public String getFormattedBreakHours(TimePicker dayStart, TimePicker dayEnd){
        StringBuilder formattedBreakHours = new StringBuilder();
        int startHour= dayStart.getCurrentHour();
        int startMin = dayStart.getCurrentMinute();
        int endHour= dayEnd.getCurrentHour();
        int endMin = dayEnd.getCurrentMinute();
        if((endHour+endMin)-(startHour+startMin)<=2){
            return  "";
        } else{
            double tempStart = startHour+(startMin==0? 0:0.5);
            float timeDifference = 0;
            while(tempStart<(endHour+(endMin==0? 0:0.5))){
                timeDifference+=0.5;
                if(timeDifference>2){
                    formattedBreakHours.append(tempStart);
                    formattedBreakHours.append(",");
                    timeDifference = 0;
                }
                tempStart+=0.5;
            }
            formattedBreakHours.setLength(formattedBreakHours.length()-1);
            return formattedBreakHours.toString();
        }
    };

    //NOTE: if allowing for more than one time slot: available times should be "13.5-15,17-18"
    //currently accepts only one input can make it an arrayList in the future
    public String getFormattedWorkingHours(TimePicker dayStart, TimePicker dayEnd){
        int startHour= dayStart.getCurrentHour();
        int startMin = dayStart.getCurrentMinute();
        int endHour= dayEnd.getCurrentHour();
        int endMin = dayEnd.getCurrentMinute();
        //Log.d(TAG,String.format("%d:%d-%d:%d",startHour,startMin,endHour,endMin));
        String startMinString = startMin==0? "0":"5";
        String startTime = startHour+"."+startMinString;
        String endMinString = endMin==0? "0":"5";
        String endTime = endHour+"."+endMinString;
        Log.d(TAG,startTime + "-" + endTime);
        return startTime + "-" + endTime;

    }

    public TimePicker getCorrespondingDayEditText(int day,boolean isStart){
        switch (day){
            case Calendar.MONDAY:
                if(isStart) return Mondaystart;
                else return Mondayend;
            case Calendar.TUESDAY:
                if(isStart) return Tuesdaystart;
                else return Tuesdayend;
            case Calendar.WEDNESDAY:
                if(isStart) return Wednesdaystart;
                else return Wednesdayend;
            case Calendar.THURSDAY:
                if(isStart) return Thursdaystart;
                else return Thursdayend;
            case Calendar.FRIDAY:
                if(isStart) return Fridaystart;
                else return Fridayend;
            case Calendar.SATURDAY:
                if(isStart) return Saturdaystart;
                else return Saturdayend;
            default:
                if(isStart) return Sundaystart;
                else return Sundayend;
        }
    }

    //only allow 30min intervals
    private void setTimePickerInterval(TimePicker timePicker) {
        try {
            Class<?> classForid = Class.forName("com.android.internal.R$id"); //get the internal class
            Field field = classForid.getField("minute"); //get the public member minute of the class
            NumberPicker  minutePicker = timePicker.findViewById(field.getInt(null));

            minutePicker.setMinValue(0);
            minutePicker.setMaxValue(1);

            List<String> displayedValues = new ArrayList<String>();
            for (int i = 0; i < 60; i += 30) {
                displayedValues.add(String.format(Locale.ENGLISH,"%02d", i));
            }
            minutePicker.setDisplayedValues(displayedValues
                    .toArray(new String[0]));
            minutePicker.setWrapSelectorWheel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyWorkingHours(){
        TimePicker startTimePicker;
        TimePicker endTimePicker;
        double startTime;
        double endTime;
        String errorDay;

        for(int i =1;i<8;i++){
            startTimePicker =  getCorrespondingDayEditText( i,true);
            endTimePicker =  getCorrespondingDayEditText( i,false);

            startTime = startTimePicker.getCurrentHour()+(startTimePicker.getCurrentHour()==0?0:0.5);
            endTime = endTimePicker.getCurrentHour()+(endTimePicker.getCurrentHour()==0?0:0.5);
            if(startTime>endTime) {
                switch (i) {
                    case Calendar.MONDAY:
                        errorDay = "Mon";
                        break;
                    case Calendar.TUESDAY:
                        errorDay = "Tues";
                        break;
                    case Calendar.WEDNESDAY:
                        errorDay = "Wed";
                        break;
                    case Calendar.THURSDAY:
                        errorDay = "Thurs";
                        break;
                    case Calendar.FRIDAY:
                        errorDay = "Fri";
                        break;
                    case Calendar.SATURDAY:
                        errorDay = "Sat";
                        break;
                    default:
                        errorDay = "Sun";
                }
                Toast.makeText(getBaseContext(), "Ending hour should come after starting hour on " + errorDay, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}