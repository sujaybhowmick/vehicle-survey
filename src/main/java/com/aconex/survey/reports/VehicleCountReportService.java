package com.aconex.survey.reports;

import com.aconex.survey.Direction;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportService extends AbstractSensorReportService<List<VehicleCountReportService.VehicleCountReportModel>> {

    private int interval;

    public VehicleCountReportService(int interval) {
        this.interval = interval;
    }


    @Override
    public List<VehicleCountReportModel> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(this.interval);
        List<VehicleCountReportModel> reportModelEntries = new ArrayList<>();
        if(sessions.isEmpty()){
            return Collections.emptyList();
        }
        for(Session session: sessions) {
            List<VehicleEntry> sessionEntries = filterEntriesForSession(vehicleEntries, session);
            for(int day = 0; day < 5; day++){
                long southBoundCount = countEntriesForDirection(sessionEntries, day, Direction.SOUTH);
                long northBoundCount = countEntriesForDirection(sessionEntries, day, Direction.NORTH);
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
