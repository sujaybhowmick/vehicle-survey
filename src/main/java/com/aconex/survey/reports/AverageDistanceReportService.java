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
public class AverageDistanceReportService extends AbstractSensorReportService<List<AverageDistanceReportService.AverageDistanceReportModel>> {


    private final int interval;

    public AverageDistanceReportService(int interval) {
        this.interval = interval;
    }

    @Override
    public List<AverageDistanceReportModel> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(interval);
        List<AverageDistanceReportModel> averageDistances = new ArrayList<>();
        if(sessions.isEmpty()) {
            return Collections.emptyList();
        }

        for(Session session: sessions){
            List<VehicleEntry> sessionEntries = filterEntriesForSession(vehicleEntries, session);
            List<VehicleEntry> southEntries = filterEntriesForDirection(sessionEntries, Direction.SOUTH);
            List<VehicleEntry> northEntries = filterEntriesForDirection(sessionEntries, Direction.NORTH);
            double distanceInSouth = getAverageDistance(southEntries);
            double distanceInNorth = getAverageDistance(northEntries);
            averageDistances.add(new AverageDistanceReportModel(session, distanceInSouth, distanceInNorth));
        }

        return averageDistances;
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.isEmpty())
            return 0;
        long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).getEntryTime().getTime();
        long timeOfFirstVehicle = entriesInTheSession.get(0).getEntryTime().getTime();
        double averageTimeGapBetweenVehicles = ((double) timeOfLastVehicle - (double) timeOfFirstVehicle) / (double)entriesInTheSession.size();
        return averageTimeGapBetweenVehicles * SensorReportService.AVG_SPEED/ TimeUtils.MILLISECONDS_IN_A_SECOND;
    }

    public class AverageDistanceReportModel {

        final Session session;

        final double averageDistanceSouth;

        final double averageDistanceNorth;

        public AverageDistanceReportModel(Session session, double averageDistanceSouth, double averageDistanceNorth) {
            this.session = session;
            this.averageDistanceSouth = averageDistanceSouth;
            this.averageDistanceNorth = averageDistanceNorth;
        }
    }
}
