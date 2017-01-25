package com.aconex.survey.reports;

import com.aconex.survey.Session;
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
        List<Session> sessions = Session.createSessions(interval);
        SpeedDistributionReportService reportService = new SpeedDistributionReportService(interval);
        List<SpeedDistributionReportService.SpeedDistributionReportItem> models = reportService.generate(entries);
        assertEquals();
    }

}