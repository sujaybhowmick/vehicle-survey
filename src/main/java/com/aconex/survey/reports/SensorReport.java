package com.aconex.survey.reports;

import com.aconex.survey.VehicleEntry;

import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */

public interface SensorReport<T> {

    T generate(List<VehicleEntry> vehicleEntries);
}
