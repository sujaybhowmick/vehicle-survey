package com.aconex.survey;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class SensorDataParserTest {

    List<String> sensorInputs;

    @BeforeClass
    public void setUp(){
        this.sensorInputs = new ArrayList<>();
        this.sensorInputs.add("A86328771");
        this.sensorInputs.add("B86328774");
        this.sensorInputs.add("A86328899");
        this.sensorInputs.add("B86328902");
        this.sensorInputs.add("A582668");
        this.sensorInputs.add("B582671");
        this.sensorInputs.add("A582787");
        this.sensorInputs.add("A582787");
    }

    @Test
    public void testValidInput(){
        SensorDataParser parser = new SensorDataParser();
        List<VehicleEntry> entries = parser.parse(this.sensorInputs);

    }
}
