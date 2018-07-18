package com.expediaCodingExercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time implements Comparable<Time>{

    // Note time from 00:00 to 23:59 ISO 8601
    private int hours; 
    private int minutes;
    
    public static int MAX_MINUTES = 59;
    public static int MAX_HOURS = 23;
    public static int MIN_MINUTES = 0;
    public static int MIN_HOURS = 0;


    public Time(String time) throws TimeException {

        if (!validTime(time)) {
            throw new TimeException();
        }
        String[] params = time.split(":");
        this.hours = Integer.parseInt(params[0]);
        this.minutes = Integer.parseInt(params[1]);
        
    }


    public Time(int hours, int minutes) throws TimeException {
        if (!validTime(hours, minutes)) {
            throw new TimeException();
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    private boolean validTime(String time) {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time);
        return m.matches();
    }

    private boolean validTime(int hours, int minutes) {
        return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
    }

    public int compareTime(Time compare) {
        if (this.getHours() < compare.getHours()) { 
            return -1;
        }
        if (this.getHours() > compare.getHours()) { 
            return 1;
        }

        if (this.getMinutes() < compare.getMinutes()) { 
            return -1;
        }
        if (this.getMinutes() > compare.getMinutes()) { 
            return 1;
        }

        return 0;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {

        //NIST Standard
        String ampmText ;
        int ampmHours = this.hours;

        if( this.hours > 12){ // PM time
            ampmHours -= 12;
            ampmText = "PM";
        }
        else if( this.hours == 0){ // 12PM time
            ampmHours = 12;
            ampmText = "PM";
        }
        else {  // AM time
            ampmText = "AM";
        }

        StringBuilder timeBuilder = new StringBuilder("");
        if (ampmHours < 10) {
            timeBuilder.append("0").append(ampmHours);
        } else {
            timeBuilder.append(ampmHours);
        }
        timeBuilder.append(":");
        if (minutes < 10) {
            timeBuilder.append("0").append(minutes);
        } else {
            timeBuilder.append(minutes);
        }
        timeBuilder.append(ampmText);
        return timeBuilder.toString();

    }

   public class TimeException extends Exception {

       public TimeException() {
           super("Invalid Time");
       }

       public TimeException(String message) {
           super(message);
       }

   }

    @Override
    public int compareTo(Time time) {
        return compareTime(time);
    }
}