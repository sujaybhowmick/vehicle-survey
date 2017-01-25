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
public class AverageDistanceReportService extends AbstractSensorReportService<List<AverageDistanceReportService.AverageDistanceReportItem>> {


    private final int interval;

    public AverageDistanceReportService(int interval) {
        this.interval = interval;
    }

    @Override
    public List<AverageDistanceReportItem> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(interval);
        List<AverageDistanceReportItem> reportItems = new ArrayList<>();
        if(sessions.isEmpty()) {
            return Collections.emptyList();
        }

        for(Session session: sessions){
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            List<VehicleEntry> southEntries = filterEntriesForDirection(entriesForSession, Direction.SOUTH);
            List<VehicleEntry> northEntries = filterEntriesForDirection(entriesForSession, Direction.NORTH);
            double distanceInSouth = getAverageDistance(southEntries);
            double distanceInNorth = getAverageDistance(northEntries);
            reportItems.add(new AverageDistanceReportItem(session, distanceInSouth, distanceInNorth, interval));
        }

        return reportItems;
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.isEmpty())
            return 0;
        long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).getEntryTime().getTime();
        long timeOfFirstVehicle = entriesInTheSession.get(0).getEntryTime().getTime();
        double averageTimeGapBetweenVehicles = ((double) timeOfLastVehicle - (double) timeOfFirstVehicle) / (double)entriesInTheSession.size();
        return averageTimeGapBetweenVehicles * SensorReportService.AVG_SPEED/ TimeUtils.MILLISECONDS_IN_A_SECOND;
    }

    public class AverageDistanceReportItem {

        public final Session session;

        public final double averageDistanceSouth;

        public final double averageDistanceNorth;

        public final int interval;

        public AverageDistanceReportItem(Session session, double averageDistanceSouth, double averageDistanceNorth, int interval) {
            this.session = session;
            this.averageDistanceSouth = averageDistanceSouth;
            this.averageDistanceNorth = averageDistanceNorth;
            this.interval = interval;
        }
    }
}
