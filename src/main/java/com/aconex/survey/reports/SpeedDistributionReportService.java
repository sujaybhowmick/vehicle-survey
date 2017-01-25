package com.aconex.survey.reports;

import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sbhowmick on 1/26/17.
 */
public class SpeedDistributionReportService extends AbstractSensorReportService<List<SpeedDistributionReportService.SpeedDistributionReportItem>> {

    private final int interval;

    public SpeedDistributionReportService(int interval) {
        this.interval = interval;
    }

    @Override
    public List<SpeedDistributionReportItem> generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(interval);
        List<SpeedDistributionReportItem> reportItems = new ArrayList<>();
        if(sessions.isEmpty()){
            Collections.emptyList();
        }

        for(Session session: sessions){
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            double totalSpeed = entriesForSession.stream().mapToDouble(VehicleEntry::speedInKmph).sum();
            double avgSpeed = 0.0;
            if(!entriesForSession.isEmpty()){
                avgSpeed = totalSpeed / entriesForSession.size();
            }
            reportItems.add(new SpeedDistributionReportItem(session, avgSpeed, interval));

        }
        return reportItems;
    }

    public class SpeedDistributionReportItem {
        public final double averageSpeed;

        public final Session session;

        public final int interval;

        public SpeedDistributionReportItem(Session session, double averageSpeed, int interval) {
            this.averageSpeed = averageSpeed;
            this.session = session;
            this.interval = interval;
        }
    }
}
