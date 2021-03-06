package com.aconex.survey;


import java.util.Date;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class VehicleEntry {

    public static final double WHEELBASE = 0.0025; // in kms

    private int day;

    private final int frontAxleTime;

    private final int backAxleTime;

    private final Direction direction;

    private final Date entryTime;

    public VehicleEntry(int day, int frontAxleTime, int backAxleTime, Direction direction) {
        this.day = day;
        this.frontAxleTime = frontAxleTime;
        this.backAxleTime = backAxleTime;
        this.direction = direction;
        this.entryTime = new Date((frontAxleTime + backAxleTime) / 2);
    }

    public int getDay() {
        return day;
    }

    public int getFrontAxleTime() {
        return frontAxleTime;
    }

    public int getBackAxleTime() {
        return backAxleTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isValid() {
        return (frontAxleTime >= 0 && frontAxleTime < TimeUtils.MAX_MILLISECONDS_IN_A_DAY
                && backAxleTime >= 0 && backAxleTime < TimeUtils.MAX_MILLISECONDS_IN_A_DAY
                && frontAxleTime < backAxleTime);
    }

    public double speedInKmph(){
        if(!isValid()){
            return 0;
        }

        double timeTaken = TimeUtils.convertToHour(backAxleTime - frontAxleTime);
        return WHEELBASE / timeTaken;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setDay(int day) {
        this.day = day;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("day = ").append(day).append("|").append("frontAxleTime = ").append(frontAxleTime).append("|")
                .append("backAxleTime = ").append(backAxleTime).append("|").append("direction = ").append(direction);
        return sb.toString();
    }
}
