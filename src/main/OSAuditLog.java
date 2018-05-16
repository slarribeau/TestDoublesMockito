package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OSAuditLog implements AuditLog {
    @Override
    public void log(String logType, String subtype, String data) {
      System.out.println("log");
      System.out.println(logType + " " + subtype + " " + data);
    }
}
