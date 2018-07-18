package com.expediaCodingExercise;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.expediaCodingExercise.Time.TimeException;

public class FlightManagerTest {

    private Application app ;

    public int HOUR_RANGE = 5;

    @Before
    public void setUp() throws Exception {
        app = new Application();
        
        TreeMap<Time, Flight> flightsTree = new TreeMap<Time, Flight>();
        
        Time t1 = new Time(07,30);
        
        Time t2 = new Time(10,30);
        
        Time t3 = new Time(14,30);
        
        Time t4 = new Time(15,00);
        
        Time t5 = new Time(23,00);
        
        Time t6 = new Time(00,30);
        
        Flight f1 = new Flight("Air Canada", 8099, t1);
        
        Flight f2 = new Flight("United Airlines", 6115, t2);
        
        Flight f3 = new Flight("WestJet", 6456, t3);
        
        Flight f4 = new Flight("Delta", 3833, t4);

        Flight f5 = new Flight("Delta", 2444, t5);

        Flight f6 = new Flight("Delta", 5666, t6);

        flightsTree.put(t1, f1);

        flightsTree.put(t2, f2);

        flightsTree.put(t3, f3);

        flightsTree.put(t4, f4);
        
        flightsTree.put(t5, f5);
        
        flightsTree.put(t6, f6);

        app.getFlightManager().resetFlights(flightsTree);
    }

    @Test
    public void test6AM() throws TimeException {

        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(6,10),HOUR_RANGE);

        assertTrue(flights.size() == 2);

    }

    @Test
    public void test12AM() throws TimeException {

        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(0,0),HOUR_RANGE);

        assertTrue(flights.size() == 1);

    }
    
    @Test
    public void test3PM() throws TimeException {

        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(3,10),HOUR_RANGE);

        assertTrue(flights.size() == 2);

    }
    
    @Test
    public void test11PM() throws TimeException {

        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(23,10),HOUR_RANGE);

        assertTrue(flights.size() == 1);

    }

    @Test
    public void test10AM() throws TimeException {

        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(10,10),HOUR_RANGE);

        assertTrue(flights.size() == 4);

    }

    @Test
    public void testEmpty() throws TimeException {

        app.getFlightManager().resetFlights(new TreeMap<Time, Flight>() );
        
        Collection<Flight> flights = app.getFlightManager().getFlightsWithinHourRange(new Time(10,10),HOUR_RANGE);

        assertTrue(flights.size() == 0);

        flights = app.getFlightManager().getFlightsWithinHourRange(new Time(0,10),HOUR_RANGE);

        assertTrue(flights.size() == 0);

        flights = app.getFlightManager().getFlightsWithinHourRange(new Time(23,10),HOUR_RANGE);

        assertTrue(flights.size() == 0);
    }

}
