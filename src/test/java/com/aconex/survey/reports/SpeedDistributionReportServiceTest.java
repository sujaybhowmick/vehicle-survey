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
        List<SpeedDistributionReportService.SpeedDistributionReportItem> items = reportService.generate(entries);
        assertEquals(2, items.size());
        SpeedDistributionReportService.SpeedDistributionReportItem item0 = items.get(0);
        SpeedDistributionReportService.SpeedDistributionReportItem item1 = items.get(1);
        assertEquals(59.85, item0.averageSpeed, 00.00521677317741);
        assertEquals(0.0, item1.averageSpeed,0.0);

    }

}