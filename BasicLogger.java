package com.expediaCodingExercise;

import java.util.Calendar;


public class BasicLogger {

    //NOTE, this could be configured through properties
    private int logLevel = 30;

    public void debug(String debugString, Throwable exc){
        debug(debugString + Helper.exceptionToString(exc), 50);
    }

    public void debug(String debugString, int debugLevel)
    {

      Calendar cal = Calendar.getInstance();

      StringBuffer buffer = new StringBuffer(100);
      buffer.append(cal.getTime());
      buffer.append('-');
      buffer.append(debugString);

      System.out.println(buffer.toString());

    }

    public void log(String logString, int logLevel)
    {
        if (logLevel >= getDebugThreshold())
        {
          debug(logString, logLevel);
        }
    }

    private int getDebugThreshold() {
        return logLevel;
    }

}
