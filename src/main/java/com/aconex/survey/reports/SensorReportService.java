package com.aconex.survey.reports;

import com.aconex.survey.VehicleEntry;

import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */

public interface SensorReportService<T> {

    static final double AVG_SPEED = 16.66; // in m/s

    T generate(List<VehicleEntry> vehicleEntries);
}
