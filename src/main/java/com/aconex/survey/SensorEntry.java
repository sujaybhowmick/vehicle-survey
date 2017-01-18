package com.aconex.survey;

import java.time.LocalTime;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class SensorEntry {
    private final int day;

    private final long seondsPastMidnight;

    private final LocalTime time;

    public SensorEntry(int day, long seondsPastMidnight) {
        this.day = day;
        this.seondsPastMidnight = seondsPastMidnight;
        this.time = TimeUtils.toTime(seondsPastMidnight);
    }

    public int getDay() {
        return day;
    }

    public long getSeondsPastMidnight() {
        return seondsPastMidnight;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorEntry that = (SensorEntry) o;

        if (day != that.day) return false;
        if (seondsPastMidnight != that.seondsPastMidnight) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + (int) (seondsPastMidnight ^ (seondsPastMidnight >>> 32));
        result = 31 * result + time.hashCode();
        return result;
    }
}
