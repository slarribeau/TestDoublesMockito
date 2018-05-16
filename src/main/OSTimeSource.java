package main;

import org.joda.time.DateTime;

public class OSTimeSource implements TimeSource {
  @Override
  public DateTime currentTime() {
    return new DateTime();
  }
}

