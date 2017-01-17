package com.aconex.survey;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/17/17.
 */
public class TimeUtilsTest {

    @Test
    public void testDurationInMillisToHours(){
        long hours = TimeUtils.toHours(268981);
        assertEquals(0, hours);
    }

    @Test
    public void testDurationInMillisToMinutes(){
        long minutes = TimeUtils.toMinutes(268981);
        assertEquals(4, minutes);
    }

    @Test
    public void testDurationInMillisToSeconds(){
        long seconds = TimeUtils.toSeconds(268981);
        assertEquals(28, seconds);
    }

    @Test
    public void testDurationInMillisToTime(){
        long hours = TimeUtils.toHours(268981);
        assertEquals(0, hours);

        long minutes = TimeUtils.toMinutes(268981);
        assertEquals(4, minutes);

        long seconds = TimeUtils.toSeconds(268981);
        assertEquals(28, seconds);

        String strTime = TimeUtils.toStringTime(hours, minutes, seconds);
        assertEquals("12:04:28", strTime);

        LocalTime localTime = TimeUtils.toTime(hours, minutes, seconds);
        LocalTime expected = LocalTime.of((int)hours, (int)minutes, (int)seconds);
        assertEquals(expected, localTime);

    }

}
