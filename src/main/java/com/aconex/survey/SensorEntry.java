package com.aconex.survey;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class SensorEntry {
    private final int day;

    private final long seondsPastMidnight;

    public SensorEntry(int day, long seondsPastMidnight) {
        this.day = day;
        this.seondsPastMidnight = seondsPastMidnight;
    }

    public int getDay() {
        return day;
    }

    public long getSeondsPastMidnight() {
        return seondsPastMidnight;
    }
}
