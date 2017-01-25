package com.aconex.survey.reports;

import com.aconex.survey.SensorDataParser;
import com.aconex.survey.VehicleEntry;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class BaseReportServiceTest {

    List<VehicleEntry> entries;

    @Before
    public void setUp(){
        List<String> inputs = new ArrayList<>();
        inputs = new ArrayList<>();
        inputs.add("A268981");
        inputs.add("A269123");
        inputs.add("A604957");
        inputs.add("B604960");
        inputs.add("A605128");
        inputs.add("B605132");
        inputs.add("A1089807");
        inputs.add("B1089810");
        inputs.add("A1089948");
        inputs.add("B1089951");

        entries = new SensorDataParser().parse(inputs);

    }
}
