package com.aconex.survey.reports;

import com.aconex.survey.SensorDataParser;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class AverageDistanceReportServiceTest extends BaseReportServiceTest {
    @Test
    public void testGenerate() throws Exception {
        List<VehicleEntry> entries = new SensorDataParser().parse(this.inputs);
        int interval = 720;
        List<Session> sessions = Session.createSessions(interval);
        AverageDistanceReportService report = new AverageDistanceReportService(interval);
        List<AverageDistanceReportService.AverageDistanceReportModel> reportModels = report.generate(entries);
        assertEquals(2, reportModels.size());
        AverageDistanceReportService.AverageDistanceReportModel model1 = reportModels.get(0);
        assertEquals(4038.67, model1.averageDistanceSouth, 0.0072200000003);
        assertEquals(0, model1.averageDistanceNorth, 0.0);

    }

}