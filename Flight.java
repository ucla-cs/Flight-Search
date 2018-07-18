package com.expediaCodingExercise;

public class Flight {

    private String airlineName;

    private int flightNumber;

    private Time departureTime;

    public Flight(String airlineName, int flightNumber, Time departureTime){
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
}
