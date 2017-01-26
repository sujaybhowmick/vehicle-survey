package com.aconex.survey.reports;

import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by sbhowmick on 1/26/17.
 */
public final class ReportWriter {

    private final List<VehicleEntry> entries;

    private final int[] intervals;

    private Writer writer;

    private int noOfDays;

    public ReportWriter(Writer writer, List<VehicleEntry> entries, int[] intervals, int noOfDays) {
        this.entries = entries;
        this.intervals = intervals;
        this.noOfDays = noOfDays;
        this.writer = writer;
    }

    public void write() throws IOException {
        for(int interval: this.intervals){
            writer.write("****** Vehicle Count Report ******\n");
            VehicleCountReportService vehicleCountReportService = new VehicleCountReportService(interval, noOfDays);
            writer.write(vehicleCountReportService.generate(this.entries));
            writer.write("\n****** Average Distance Report ******\n");
            AverageDistanceReportService averageDistanceReportService = new AverageDistanceReportService(interval);
            writer.write(averageDistanceReportService.generate(this.entries));
            writer.write("\n****** Speed Distribution Report ******\n");
            SpeedDistributionReportService speedDistributionReportService = new SpeedDistributionReportService(interval);
            writer.write(speedDistributionReportService.generate(this.entries));
        }

    }
}
