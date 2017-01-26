package com.aconex.survey.reports;

import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.List;

/**
 * Created by sbhowmick on 1/26/17.
 */
public class SpeedDistributionReportService extends AbstractSensorReportService {

    private final int interval;

    public SpeedDistributionReportService(int interval) {
        this.interval = interval;
    }

    @Override
    public String generate(List<VehicleEntry> vehicleEntries) {
        List<Session> sessions = Session.createSessions(interval);
        StringBuilder sb = new StringBuilder();

        if(sessions.isEmpty()){
            return "";
        }

        for(Session session: sessions){
            List<VehicleEntry> entriesForSession = filterEntriesForSession(vehicleEntries, session);
            double totalSpeed = entriesForSession.stream().mapToDouble(VehicleEntry::speedInKmph).sum();
            double averageSpeed = 0.0;
            if (!entriesForSession.isEmpty())
                averageSpeed = totalSpeed/entriesForSession.size();
            sb.append(session).append("| Average speed = ").append(averageSpeed).append('\n');

        }
        return sb.toString();
    }
}
