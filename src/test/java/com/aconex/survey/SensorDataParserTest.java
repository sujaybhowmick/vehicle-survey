package com.aconex.survey;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sbhowmick on 1/24/17.
 */
public class SensorDataParserTest {

    List<String> sensorInputs;

    @Before
    public void setUp(){
        this.sensorInputs = new ArrayList<>(Arrays.asList("A268981", "A269123", "A604957", "B604960",
                "A605128", "B605132", "A1089807", "B1089810", "A1089948", "B1089951"));
    }

    @Test
    public void testCountVehicleEntryInNorthDirection(){
        List<VehicleEntry> entries = new SensorDataParser().parse(this.sensorInputs);
        long actualCount = entries.stream().filter(vehicleEntry -> vehicleEntry.getDirection() == Direction.NORTH).count();
        assertEquals(1, actualCount);
    }

    @Test
    public void testCountVehicleEntryInSouthDirection(){
        List<VehicleEntry> entries = new SensorDataParser().parse(this.sensorInputs);
        long actualCount = entries.stream().filter(vehicleEntry -> vehicleEntry.getDirection() == Direction.SOUTH).count();
        assertEquals(2, actualCount);
    }

}
