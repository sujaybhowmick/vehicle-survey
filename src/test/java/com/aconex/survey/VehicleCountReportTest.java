package com.aconex.survey;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class VehicleCountReportTest {


    List<String> inputs;

    @Before
    public void setUp(){
        this.inputs = new ArrayList<>();
        this.inputs.add("A268981");
        this.inputs.add("A269123");
        this.inputs.add("A604957");
        this.inputs.add("B604960");
        this.inputs.add("A605128");
        this.inputs.add("B605132");
        this.inputs.add("A1089807");
        this.inputs.add("B1089810");
        this.inputs.add("A1089948");
        this.inputs.add("B1089951");

    }


    @Test
    public void testVehicleCountMorning(){
        List<VehicleEntry> entries = new SensorDataParser().parse(this.inputs);
        int interval = 720;
        List<Session> sessions = Session.createSessions(interval);
        VehicleCountReport report = new VehicleCountReport(interval);

    }
}
