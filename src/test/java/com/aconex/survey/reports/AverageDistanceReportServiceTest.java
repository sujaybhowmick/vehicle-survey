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
        AverageDistanceReportService averageDistanceReportService = new AverageDistanceReportService(interval);
        String report = averageDistanceReportService.generate(entries);
        assertNotNull(report);


    }

}