package com.aconex.survey;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class SensorDataReaderTest {

    StringBuilder input = new StringBuilder();
    @Before
    public void setUp(){
        input.append("A268981").append("\n");
        input.append("A269123").append("\n");
    }

    @Test
    public void testReadLines() throws Exception {
        StringReader reader = new StringReader(input.toString());
        List<String> lines = SensorDataReader.readLines(reader);
        assertEquals(2, lines.size());
    }

}