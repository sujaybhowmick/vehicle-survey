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
        StringBuilder expected = new StringBuilder();
        expected.append("Session from 00:00:00 to 12:00:00| Average distance between cars in south direction = 4038.6672200000003 meters, in north direction = 0.0 meters")
                .append('\n');
        expected.append("Session from 12:00:00 to 00:00:00| Average distance between cars in south direction = 0.0 meters, in north direction = 0.0 meters")
                .append('\n');
        assertEquals(expected.toString(), report.toString());
    }
}