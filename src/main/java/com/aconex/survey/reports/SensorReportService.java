package com.aconex.survey.reports;

import com.aconex.survey.VehicleEntry;

import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */

public interface SensorReportService {

    double AVG_SPEED = 16.66; // in m/s

    String generate(List<VehicleEntry> vehicleEntries);
}
