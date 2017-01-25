package com.aconex.survey.reports;

import com.aconex.survey.Direction;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReport implements SensorReport<List<VehicleCountReport.VehicleCountReportModel>>{

    private int interval;

    public VehicleCountReport(int interval) {
        this.interval = interval;
    }


    @Override
    public List<VehicleCountReportModel> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(this.interval);
        StringBuilder output = new StringBuilder();
        List<VehicleCountReportModel> reportModelEntries = new ArrayList<>();
        for(Session session: sessions) {
            long totalCount = 0;
            List<VehicleEntry> sessionEntries = filterEntriesForSession(vehicleEntries, session);
            output.append(session.toString());
            for(int day = 0; day < 5; day++){
                long southBoundCount = countEntriesForDirection(sessionEntries, day, Direction.SOUTH);
                long northBoundCount = countEntriesForDirection(sessionEntries, day, Direction.NORTH);
                output.append(" | Day ").append(day).append(" Count South = ").append(southBoundCount)
                        .append(" North = ").append(northBoundCount);
                totalCount += southBoundCount + northBoundCount;
                VehicleCountReportModel reportModel = new VehicleCountReportModel(session, southBoundCount, northBoundCount);
                reportModelEntries.add(reportModel);
            }
        }
        return reportModelEntries;
    }

    private long countEntriesForDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().filter(vehicleEntry -> vehicleEntry.getDay() == day
        && vehicleEntry.getDirection() == direction).count();
    }

    private List<VehicleEntry> filterEntriesForSession(List<VehicleEntry> entries, Session session){
        return entries.stream()
                .filter(vehicleEntry ->
                        vehicleEntry.getEntryTime().compareTo(session.startTime()) >= 0
                        && vehicleEntry.getEntryTime().compareTo(session.endTime()) < 0).collect(Collectors.toList());
    }

    private List<VehicleEntry> filterEntriesForDirection(List<VehicleEntry> entries, Direction direction){
        return entries.stream().filter(vehicleEntry ->
                vehicleEntry.getDirection() == direction).collect(Collectors.toList());
    }

    public class VehicleCountReportModel {
        public final Session session;

        public long southCount;

        public long northCount;

        public VehicleCountReportModel(Session session, long southCount, long northCount) {
           this.session = session;
            this.southCount = southCount;
            this.northCount = northCount;
        }
    }
}
