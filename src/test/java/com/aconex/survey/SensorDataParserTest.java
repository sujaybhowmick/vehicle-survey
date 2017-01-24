package com.aconex.survey;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class SensorDataParserTest {

    List<String> sensorInputs;

    @Before
    public void setUp(){
        this.sensorInputs = new ArrayList<>();
        this.sensorInputs.add("A268981");
        this.sensorInputs.add("A269123");
        this.sensorInputs.add("A604957");
        this.sensorInputs.add("B604960");
        this.sensorInputs.add("A605128");
        this.sensorInputs.add("B605132");
        this.sensorInputs.add("A1089807");
        this.sensorInputs.add("B1089810");
        this.sensorInputs.add("A1089948");
        this.sensorInputs.add("B1089951");

    }

    @Test
    public void testValidInput(){
        SensorDataParser parser = new SensorDataParser();
        List<VehicleEntry> entries = parser.parse(this.sensorInputs);
        int actual = entries.size();
        int expected = 3;
        assertEquals(expected, actual);

        entries = parser.parse(this.sensorInputs.subList(2, sensorInputs.size()));
        assertEquals(2, entries.size());

        entries = parser.parse(this.sensorInputs.subList(6, sensorInputs.size()));
        assertEquals(1, entries.size());

    }
}
