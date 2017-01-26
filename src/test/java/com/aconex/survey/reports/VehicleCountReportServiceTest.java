package com.aconex.survey.reports;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportServiceTest extends BaseReportServiceTest {


    @Test
    public void testGenerate(){
        int interval = 720;
        VehicleCountReportService vehicleCountReportService = new VehicleCountReportService(interval, 5);
        String report = vehicleCountReportService.generate(entries);
        assertNotNull(report);
        StringBuilder expected = new StringBuilder();
        expected.append("| Day 0 Count South = 2 Count North = 1| Day 1 Count South = 0 Count North = 0| Day 2 Count South = 0 Count North = 0| Day 3 Count South = 0 Count North = 0| Day 4 Count South = 0 Count North = 0")
                .append('\n')
                .append("| Day 0 Count South = 0 Count North = 0| Day 1 Count South = 0 Count North = 0| Day 2 Count South = 0 Count North = 0| Day 3 Count South = 0 Count North = 0| Day 4 Count South = 0 Count North = 0")
                .append('\n').append('\n')
                .append("Session from 00:00:00 to 12:00:00 is peak session with vehicle count = 3");

        assertEquals(expected.toString(), report.toString());
    }
}
