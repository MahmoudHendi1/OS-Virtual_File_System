package Persitience;

public interface DataStreamer {
  void save(Object oj, String filePath);
   Object read(String filePath);
}
