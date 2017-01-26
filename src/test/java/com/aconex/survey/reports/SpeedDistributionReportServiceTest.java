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
        assertNotNull(report);

    }

}