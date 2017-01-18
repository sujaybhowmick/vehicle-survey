package com.aconex.survey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        sensorA.addEntry(new SensorEntry(1, 268981));
        sensorA.addEntry(new SensorEntry(1, 269123));
        Set<SensorEntry> entries = sensorA.getEntries();
        assertEquals(2, entries.size());
        LocalTime localTime = TimeUtils.toTime(268981);
        long count = entries.stream().filter(item -> item.getTime().equals(localTime)).count();
        assertEquals(1, count);
        List<SensorEntry> list = entries.stream().filter(item -> item.getTime().equals(localTime)).collect(Collectors.toList());
        assertEquals(new SensorEntry(1, 268981), list.get(0));
    }
}
