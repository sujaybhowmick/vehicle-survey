package com.aconex.survey;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by sbhowmick on 1/18/17.
 */
public class TimeUtils {
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
}
