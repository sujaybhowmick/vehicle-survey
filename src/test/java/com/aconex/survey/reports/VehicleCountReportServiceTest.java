package com.aconex.survey.reports;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportServiceTest extends BaseReportServiceTest {


    @Test
    public void testGenerate(){
        int interval = 720;
        VehicleCountReportService report = new VehicleCountReportService(interval);
        List<VehicleCountReportService.VehicleCountReportItem> items = report.generate(entries);
        assertEquals(10, items.size());


    }
}
