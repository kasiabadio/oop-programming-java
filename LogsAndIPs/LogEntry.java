package LogsAndIPs;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of LogEntry here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;
    
    public LogEntry(String ip, Date time, String req, int status, int bytes){
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }
    
    public String getIpAddress(){
        return ipAddress;
    }
    
    public Date getAccessTime(){
        return accessTime;
    }
    
    public String getRequest(){
        return request;
    }
    
    public int getStatusCode(){
        return statusCode;
    }
    
    public int getBytesReturned(){
        return bytesReturned;
    }
    
    public String toString(){
        return ipAddress + " " + accessTime + " " + request + " " +
        statusCode + " " + bytesReturned;
    }
}
