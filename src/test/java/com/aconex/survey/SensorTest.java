package com.aconex.survey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class SensorTest {

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testAddSensorEntry(){
        PneumaticSensor sensorA = new PneumaticSensor("A");
        sensorA.addEntry(new SensorEntry(268981, 1));
        Set<SensorEntry> entries = sensor.fetchEntries();
        assertEquals(1, entries.size());
    }
}
