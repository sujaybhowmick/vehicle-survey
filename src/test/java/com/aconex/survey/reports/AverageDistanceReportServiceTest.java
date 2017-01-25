package com.aconex.survey.reports;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class AverageDistanceReportServiceTest extends BaseReportServiceTest {
    @Test
    public void testGenerate() throws Exception {
        int interval = 720;
        AverageDistanceReportService report = new AverageDistanceReportService(interval);
        List<AverageDistanceReportService.AverageDistanceReportItem> items = report.generate(entries);
        assertEquals(2, items.size());
        AverageDistanceReportService.AverageDistanceReportItem model1 = items.get(0);
        assertEquals(4038.67, model1.averageDistanceSouth, 0.0072200000003);
        assertEquals(0, model1.averageDistanceNorth, 0.0);

    }

}