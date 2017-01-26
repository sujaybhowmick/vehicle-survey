package com.aconex.survey;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class Session {
    private final Date startTime;

    private final Date endTime;

    private Session(Date startTime, Date endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static List<Session> createSessions(int intervalMins){
        List<Session> sessions = new ArrayList<>();
        int minsInDay = TimeUtils.HOURS_IN_A_DAY * TimeUtils.MINUTES_IN_A_HOUR;
        if(minsInDay % intervalMins != 0){
            System.out.println(String.format("Interval of %d mins cannot be distributed evenly", intervalMins));
            return sessions;
        }
        int sessionCount = minsInDay / intervalMins;
        for(int i = 0; i < sessionCount; i++){
            Date startTime = new Date(i * intervalMins * TimeUtils.SECONDS_IN_A_MINUTE * TimeUtils.MILLISECONDS_IN_A_SECOND);
            Date endTime = new Date((i+1) * intervalMins * TimeUtils.SECONDS_IN_A_MINUTE * TimeUtils.MILLISECONDS_IN_A_SECOND);
            sessions.add(new Session(startTime, endTime));
        }
        return sessions;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

        return "Session from " + sdf.format(startTime) + " to " + sdf.format(endTime);
    }

    public Date endTime() {
        return endTime;
    }

    public Date startTime() {
        return startTime;
    }
}
