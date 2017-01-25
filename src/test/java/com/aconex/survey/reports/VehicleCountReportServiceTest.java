package com.aconex.survey.reports;

import com.aconex.survey.SensorDataParser;
import com.aconex.survey.Session;
import com.aconex.survey.VehicleEntry;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportServiceTest extends BaseReportServiceTest {


    @Test
    public void testVehicleCountMorning(){
        List<VehicleEntry> entries = new SensorDataParser().parse(this.inputs);
        int interval = 720;
        List<Session> sessions = Session.createSessions(interval);
        VehicleCountReportService report = new VehicleCountReportService(interval);
        List<VehicleCountReportService.VehicleCountReportModel> reportModels = report.generate(entries);
        assertEquals(10, reportModels.size());


    }
}
