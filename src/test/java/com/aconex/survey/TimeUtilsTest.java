package com.aconex.survey;

import org.junit.Test;
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
        assertEquals("00:04:28", strTime);

        LocalTime localTime = TimeUtils.toTime(0, 4, 28);
        LocalTime expected = LocalTime.of((int)hours, (int)minutes, (int)seconds);
        assertEquals(expected, localTime);

    }

    @Test
    public void testConvertToHour(){
        double inHours = TimeUtils.convertToHour(3600000);
        assertEquals(1.00, inHours, 0.0);
    }

}
