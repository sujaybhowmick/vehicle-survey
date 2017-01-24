package com.aconex.survey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class VehicleEntryTest {

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testVehicleEntry(){
        VehicleEntry vehicleEntry = new VehicleEntry(0, 86328771, 86328899, Direction.SOUTH);
        assertNotNull(vehicleEntry);

    }

    @Test
    public void testIsValid(){
        VehicleEntry vehicleEntry = new VehicleEntry(0, 86328771, 86328899, Direction.SOUTH);
        assertTrue(vehicleEntry.isValid());
    }


}
