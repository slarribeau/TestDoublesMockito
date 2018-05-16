package main;

public interface AuditLog {
  void log(String logType, String subtype, String data);
}
