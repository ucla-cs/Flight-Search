package com.expediaCodingExercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.expediaCodingExercise.Time.TimeException;

public class TimeTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNewTime10AMToString() throws TimeException {
        Time t = new Time(10,10);
        
        assertTrue(t.toString().equals("10:10AM"));
    }

    @Test
    public void testNewTime12PMToString() throws TimeException {
        Time t = new Time(0,10);

        assertTrue(t.toString().equals("12:10PM"));
    }

    @Test
    public void testNewTime11PMToString() throws TimeException {
        Time t = new Time(23,10);

        assertTrue(t.toString().equals("11:10PM"));
    }
    
    @Test
    public void testNewTime4AMToString() throws TimeException {
        Time t = new Time(12,10);

        assertTrue(t.toString().equals("12:10AM"));
    }
    
    @Test (expected = TimeException.class)
    public void testNewTimeFail() throws TimeException {
        Time t = new Time(0,100);
    }
    
    @Test (expected = TimeException.class)
    public void testNewTimeFail2() throws TimeException {
        Time t = new Time(50,0);
    }
    
    @Test (expected = TimeException.class)
    public void testNewTimeFailNegative() throws TimeException {
        Time t = new Time(0,-1);
    }
    
    @Test (expected = TimeException.class)
    public void testNewTimeFailNegative2() throws TimeException {
        Time t = new Time(-1,10);

    }
    
    @Test
    public void testCompareTimes() throws TimeException {
        Time t = new Time(12,10);

        Time t2 = new Time(13,10);
        
        Time t3 = new Time(9,10);
        
        assertTrue(t.compareTime(t2)<0);
        assertTrue(t.compareTime(t3)>0);
        assertTrue(t.compareTime(t)==0);

    }

    @Test
    public void testStringToTime23() throws TimeException {
        Time t = new Time("23:00");

        assertTrue(t.getHours() == 23);
        assertTrue(t.getMinutes() == 00);

    }

    @Test
    public void testStringToTime10() throws TimeException {
        Time t = new Time("10:44");

        assertTrue(t.getHours() == 10);
        assertTrue(t.getMinutes() == 44);

    }

    @Test (expected = TimeException.class)
    public void testStringToTimeFail() throws TimeException {
        Time t = new Time("23");

        assertTrue(t.getHours() == 23);
        assertTrue(t.getMinutes() == 00);

    }

    @Test (expected = TimeException.class)
    public void testStringToTimeFail2() throws TimeException {
        Time t = new Time("23:");

        assertTrue(t.getHours() == 23);
        assertTrue(t.getMinutes() == 00);

    }
}
