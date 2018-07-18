package com.expediaCodingExercise;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Helper {

    public static String exceptionToString(Throwable throwable)
    {
      String output = "";

      if (throwable != null)
      {
        StringWriter stringWriter = new StringWriter();
        PrintWriter stringPrinter = new PrintWriter(stringWriter);
        
        throwable.printStackTrace(stringPrinter);
        
        output = stringWriter.toString();
      }

      return output;
    }

}
