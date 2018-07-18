package com.expediaCodingExercise;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expediaCodingExercise.Time.TimeException;


/**
 * Servlet implementation class FlightSearchServlet
 */
@WebServlet("/FlightSearch")
public class FlightSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Application application = Application.getInstance();

    @Override
    public void init(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> managerInit = executor.submit(new Runnable() {
            @Override
            public void run() {
                application.init();
            }
        });
        executor.shutdown();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handleFlightSearch(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handleFlightSearch(request,response);
    }

    private void handleFlightSearch(HttpServletRequest request, HttpServletResponse response){

        //NOTE format for time is HH:MM from 00:00 to 23:59
        String time = request.getParameter("time");

        if(time == null){
            return;
        }

        //Parse Time
        Time inputTime = null;

        try {
            inputTime = new Time(time);
        } catch (TimeException e) {
            StringBuilder output = new StringBuilder();
            output.append("Input time \"");
            output.append(time);
            output.append("\" Was Invalid \"");
            application.getBasicLogger().debug(output.toString(), e);
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("{\"flights\" : [ ");

        //note HOUR RANGE could be a property instead of Hardcoded
        Collection<Flight> resultFlights = application.getFlightManager().getFlightsWithinHourRange(inputTime, 5);

        for(Flight flight : resultFlights){

            output.append("{"); 
            output.append("\"flight\" : \""+ flight.getAirlineName() + " " +flight.getFlightNumber() + "\"");
            output.append(",\"departure\" : \""+ flight.getDepartureTime().toString() + "\"");

            output.append("},");

        }

        
        output.setLength(output.length()-1);
        output.append("]}");

        try {
            PrintWriter writer = response.getWriter();
            writer.print(output.toString());
        }
        catch (IOException ioexc) {
            application.getBasicLogger().debug("IOException on print().", ioexc);
        }

        return;
    }
}
