package com.aconex.survey.reports;

import com.aconex.survey.Direction;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportService extends AbstractSensorReportService {

    private final int interval;

    private final int noOfDays;

    public VehicleCountReportService(int interval, int noOfDays) {
        this.interval = interval;
        this.noOfDays = noOfDays;
    }


    @Override
    public String generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(this.interval);
        int totalCount = 0;
        int peakCount = 0;
        Session peakSession = null;
        StringBuilder sb = new StringBuilder();

        if(sessions.isEmpty()){
            return "";
        }
        for(Session session: sessions) {
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            for(int day = 0; day < this.noOfDays; day++){
                long southBoundCount = countEntriesForDirection(entriesForSession, day, Direction.SOUTH);
                long northBoundCount = countEntriesForDirection(entriesForSession, day, Direction.NORTH);
                totalCount += southBoundCount + northBoundCount;
                sb.append("| Day ").append(day).append(" Count South = ").append(southBoundCount).append(" Count North = ")
                        .append(northBoundCount);
            }
            sb.append('\n');
            if(totalCount > peakCount){
                peakCount = totalCount;
                peakSession = session;
            }
        }
        sb.append('\n').append(peakSession).append(" is peak session with vehicle count = ").append(totalCount);
        return sb.toString();
    }

    private long countEntriesForDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().filter(vehicleEntry -> vehicleEntry.getDay() == day
        && vehicleEntry.getDirection() == direction).count();
    }




}
