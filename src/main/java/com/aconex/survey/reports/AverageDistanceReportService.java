package com.aconex.survey.reports;

import com.aconex.survey.Direction;
import com.aconex.survey.Session;
import com.aconex.survey.TimeUtils;
import com.aconex.survey.VehicleEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class AverageDistanceReportService extends AbstractSensorReportService {


    private final int interval;

    public AverageDistanceReportService(int interval) {
        this.interval = interval;
    }

    @Override
    public String generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(interval);
        StringBuilder sb = new StringBuilder();

        if(sessions.isEmpty()) {
            return "";
        }

        for(Session session: sessions){
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            List<VehicleEntry> southEntries = filterEntriesForDirection(entriesForSession, Direction.SOUTH);
            List<VehicleEntry> northEntries = filterEntriesForDirection(entriesForSession, Direction.NORTH);
            double distanceInSouth = getAverageDistance(southEntries);
            double distanceInNorth = getAverageDistance(northEntries);
            sb.append(session).append("| Average distance between cars in south direction = ").append(distanceInSouth)
                    .append(" meters, in north direction = ").append(distanceInNorth).append(" meters").append('\n');
        }
        return sb.toString();
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.isEmpty())
            return 0;
        long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).getEntryTime().getTime();
        long timeOfFirstVehicle = entriesInTheSession.get(0).getEntryTime().getTime();
        double averageTimeGapBetweenVehicles = ((double) timeOfLastVehicle - (double) timeOfFirstVehicle) / (double)entriesInTheSession.size();
        return averageTimeGapBetweenVehicles * SensorReportService.AVG_SPEED/ TimeUtils.MILLISECONDS_IN_A_SECOND;
    }
}
