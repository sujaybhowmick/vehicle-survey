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
public class VehicleCountReportService extends AbstractSensorReportService<List<VehicleCountReportService.VehicleCountReportItem>> {

    private int interval;

    public VehicleCountReportService(int interval) {
        this.interval = interval;
    }


    @Override
    public List<VehicleCountReportItem> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(this.interval);
        List<VehicleCountReportItem> reportItems = new ArrayList<>();
        if(sessions.isEmpty()){
            return Collections.emptyList();
        }
        for(Session session: sessions) {
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            for(int day = 0; day < 5; day++){
                long southBoundCount = countEntriesForDirection(entriesForSession, day, Direction.SOUTH);
                long northBoundCount = countEntriesForDirection(entriesForSession, day, Direction.NORTH);
                VehicleCountReportItem reportModel = new VehicleCountReportItem(session, southBoundCount, northBoundCount, interval);
                reportItems.add(reportModel);
            }
        }
        return reportItems;
    }

    private long countEntriesForDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().filter(vehicleEntry -> vehicleEntry.getDay() == day
        && vehicleEntry.getDirection() == direction).count();
    }



    public class VehicleCountReportItem {
        public final Session session;

        public final long southCount;

        public final long northCount;

        public final int interval;



        public VehicleCountReportItem(Session session, long southCount, long northCount, int interval) {
            this.session = session;
            this.southCount = southCount;
            this.northCount = northCount;
            this.interval = interval;
        }
    }

}
