package com.expediaCodingExercise;

import java.util.Collection;
import java.util.TreeMap;

import com.expediaCodingExercise.Time.TimeException;


public class FlightManager {

    private  Application  application;

    private BasicLogger log;

    private TreeMap<Time, Flight> flightsTree = new TreeMap<Time, Flight>();

    public void init(){
        loadFlights();
    }

    public FlightManager(Application application, BasicLogger logger){
        this.application = application;
        this.log = logger;
    }

    public Collection<Flight> getFlightsWithinHourRange(Time inputTime, int hourRange){

        Collection<Flight> results = null;

        Time fromTime;
        Time toTime;

        try {
            int fromHour = Time.MIN_HOURS, fromMinute = Time.MIN_MINUTES;
            int toHour = Time.MAX_HOURS , toMinute = Time.MAX_MINUTES;

            if(inputTime.getHours() >= hourRange){
                fromHour = inputTime.getHours() - hourRange;
                fromMinute = inputTime.getMinutes();
            }

            if(inputTime.getHours() < Time.MAX_HOURS - hourRange){
                toHour = inputTime.getHours() + hourRange;
                toMinute = inputTime.getMinutes();
            }

            fromTime = new Time(fromHour, fromMinute);
            toTime = new Time(toHour, toMinute);

            results = flightsTree.subMap(fromTime, toTime).values();

        } catch (TimeException e) {
            log.debug("Time doesn't fit within the normal range", e);
        }

        return results;
    }

    protected void resetFlights(TreeMap<Time, Flight> newFlights){
        this.flightsTree = newFlights;
    }

    private void loadFlights() {

        try {
            
            //NORMALLY WOULD COME FROM THE DATABASE
            Time t1 = new Time(07,30);
            
            Time t2 = new Time(10,30);
            
            Time t3 = new Time(14,30);
            
            Time t4 = new Time(15,00);
            
            Flight f1 = new Flight("Air Canada", 8099, t1);
            
            Flight f2 = new Flight("United Airlines", 6115, t2);
            
            Flight f3 = new Flight("WestJet", 6456, t3);
            
            Flight f4 = new Flight("Delta", 3833, t4);
    
            flightsTree.put(t1, f1);
    
            flightsTree.put(t2, f2);
            
            flightsTree.put(t3, f3);
            
            flightsTree.put(t4, f4);
        
        } catch (TimeException e) {
            application.getBasicLogger().debug("Invalid Times from loadFlights()", e);
        }

        //TODO Load the flights from the database
        /*
        DatabaseConnector dbConn = application.getConnector();

        dbConn.setQuery("SELECT Airline"
                + "           , FlightNumber"
                + "           , Departure"
                + "        FROM flights;");

        dbConn.runQuery();

        while (dbConn.more()) {
            int flightID = dbConn.getInt("FlightID");

            String airlineName = dbConn.getField("AirLineLine","");

            String flightNumber = dbConn.getField("FlightNumber","");

            Time departureTime = dbConn.getTime("DepartureTime","");

            Flight flight = new Flight(flightID, airlineName,flightNumber,departureTime);

            flightsTree.add( departureTime , flight);

            dbConn.next();

        }

        log.debug("Total Flights Loaded: " + dbConn.getRowCount());

        dbConn.close();
        */

    }
}
