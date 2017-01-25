package com.aconex.survey;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbhowmick on 1/25/17.
 */
public class SessionTest {

    int intervalsInMins[] = {720, 60, 30, 20, 15};

    @Before
    public void setUp(){

    }

    @Test
    public void testCreateSessions() throws Exception {
        int minsInDay = TimeUtils.HOURS_IN_A_DAY * TimeUtils.MINUTES_IN_A_HOUR;
        for(int interval: intervalsInMins){
            int expectedSessions = minsInDay / interval;
            List<Session> sessions = Session.createSessions(interval);
            assertEquals(expectedSessions, sessions.size());

        }
    }

}