package com.expediaCodingExercise;


public class Application {

    private static Application application = new Application();

    private BasicLogger basicLogger;
    private FlightManager flightManager;

    //Also could include Properties, SessionManager, DatabaseManager, CreditCardManager ;

    public static Application getInstance() {
        return application;
    }

    public Application(){
        basicLogger = new BasicLogger();

        flightManager = new FlightManager(this,basicLogger);

    }

    void init(){
        flightManager.init();
    }

    public FlightManager getFlightManager() {
        return flightManager;
    }

    public BasicLogger getBasicLogger(){
        return basicLogger;
    }
}
