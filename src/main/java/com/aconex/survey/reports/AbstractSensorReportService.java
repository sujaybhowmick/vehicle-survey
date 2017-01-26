package com.aconex.survey.reports;

import com.aconex.survey.Direction;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbhowmick on 1/25/17.
 */
public abstract class AbstractSensorReportService<T> implements SensorReportService {
    protected List<VehicleEntry> filterEntriesForSession(List<VehicleEntry> entries, Session session){
        return entries.stream()
                .filter(vehicleEntry ->
                        vehicleEntry.getEntryTime().compareTo(session.startTime()) >= 0
                        && vehicleEntry.getEntryTime().compareTo(session.endTime()) < 0).collect(Collectors.toList());
    }

    protected List<VehicleEntry> filterEntriesForDirection(List<VehicleEntry> entries, Direction direction){
        return entries.stream().filter(vehicleEntry ->
                vehicleEntry.getDirection() == direction).collect(Collectors.toList());
    }
}
