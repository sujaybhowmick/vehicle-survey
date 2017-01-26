package com.aconex.survey.reports;

import com.aconex.survey.Direction;
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
        entries = new ArrayList<>();
        entries.add(new VehicleEntry(0, 268981, 269123, Direction.NORTH));
        entries.add(new VehicleEntry(0, 604958, 605130, Direction.SOUTH));
        entries.add(new VehicleEntry(0, 1089808, 1089949, Direction.SOUTH));

    }
}
