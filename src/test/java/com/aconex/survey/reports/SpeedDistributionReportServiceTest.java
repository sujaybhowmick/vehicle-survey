package com.aconex.survey.reports;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/26/17.
 */
public class SpeedDistributionReportServiceTest extends BaseReportServiceTest {


    @Test
    public void testGenerate(){
        int interval = 720;
        SpeedDistributionReportService reportService = new SpeedDistributionReportService(interval);
        String report = reportService.generate(entries);
        StringBuilder expected = new StringBuilder();
        expected.append("Session from 00:00:00 to 12:00:00| Average speed = 59.84521677317741").append('\n');
        expected.append("Session from 12:00:00 to 00:00:00| Average speed = 0.0").append('\n');
        assertEquals(expected.toString(), report.toString());

    }

}