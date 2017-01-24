package com.aconex.survey;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by sbhowmick on 1/18/17.
 */
public class TimeUtils {

    public static final int HOURS_IN_A_DAY = 24;
    public static final int MINUTES_IN_A_HOUR = 60;
    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final int MILLISECONDS_IN_A_SECOND = 1000;
    public static final int MAX_MILLISECONDS_IN_A_DAY = HOURS_IN_A_DAY * MINUTES_IN_A_HOUR * SECONDS_IN_A_MINUTE * MILLISECONDS_IN_A_SECOND;


    private TimeUtils(){}

    public static long toHours(long durationInMillis) {
        return TimeUnit.MILLISECONDS.toHours(durationInMillis);
    }

    public static long toMinutes(long durationInMillis) {
        return TimeUnit.MILLISECONDS.toMinutes(durationInMillis);
    }


    public static long toSeconds(long durationInMillis) {
        return TimeUnit.MILLISECONDS.toSeconds(durationInMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationInMillis));
    }

    public static String toStringTime(long hours, long minutes, long seconds) {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static LocalTime toTime(long hours, long minutes, long seconds) {
        return LocalTime.of((int)hours, (int)minutes, (int)seconds);
    }

    public static LocalTime toTime(long durationInMillis){
        long hours = toHours(durationInMillis);
        long minutes = toMinutes(durationInMillis);
        long seconds = toSeconds(durationInMillis);
        return toTime(hours, minutes, seconds);
    }

    public static double convertToHour(int milliseconds){
        if (milliseconds <0 )
            return -1;
        double minutesInHour = MINUTES_IN_A_HOUR;
        double secondsInMinute = SECONDS_IN_A_MINUTE;
        double milliSecondsInSecond = MILLISECONDS_IN_A_SECOND;
        return ((double)milliseconds)/(milliSecondsInSecond * secondsInMinute * minutesInHour);
    }

    public static int parseMillisFromInput(String input){
        try {
            int milliSeconds = Integer.parseInt(input.substring(1));
            if (milliSeconds < 0 )
                return -1;
            return milliSeconds;
        }catch (NumberFormatException e){
            System.out.println(String.format("Error parsing time from %s: ", input));
        }
        return -1;
    }
}
