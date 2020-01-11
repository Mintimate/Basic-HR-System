package com.Mintimate.tool;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Print_Error {
    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
//    public static Log log = LogFactory.getLog(Print_Error.class);
