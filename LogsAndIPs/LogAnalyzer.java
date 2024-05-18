package LogsAndIPs;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of LogAnalyzer here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(){
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            records.add(WebLogParser.parseEntry(line));
        }
    }
    
    public void printAll(){
        for (int i = 0; i < records.size(); i++){
            System.out.println(records.get(i));
        }
    }
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num){
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        for (LogEntry le: records){
            Date accessTime = le.getAccessTime();
            String date = accessTime.toString();
            String[] splitted = date.split("\\s+");
            String monthDay = splitted[1] + " " + splitted[2];
            if (monthDay.equals(someday)){
                String ipAddr = le.getIpAddress();
                if (!uniqueIPsOnDay.contains(ipAddr)){
                    uniqueIPsOnDay.add(ipAddr);
                }
            }
        }
        return uniqueIPsOnDay;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        int count = 0;
        ArrayList<String> ips = new ArrayList<String>();
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high){
                String ipAddr = le.getIpAddress();
                if (!ips.contains(ipAddr)){
                    ips.add(ipAddr);
                    count += 1;
                }
            }
        }
        return count;
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> visitsPerIP = new HashMap<String, Integer>();
        for (LogEntry le: records){
            String ip = le.getIpAddress();
            if (!visitsPerIP.containsKey(ip)){
                visitsPerIP.put(ip, 1);
            } else {
                visitsPerIP.put(ip, visitsPerIP.get(ip) + 1);
            }
        }
        return visitsPerIP;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> visitsPerIp){
        int max = -1;
        for (String key: visitsPerIp.keySet()){
            int currentValue = visitsPerIp.get(key);
            if (currentValue > max){
                max = currentValue;
            }
        }
        return max;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitsPerIp){
        ArrayList<String> ipMaxVisits = new ArrayList<String>();
        int max = mostNumberVisitsByIP(visitsPerIp);
        for (String key: visitsPerIp.keySet()){
            if (visitsPerIp.get(key) == max){
                ipMaxVisits.add(key);
            }
        }
        return ipMaxVisits;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> dayIps = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records){
            Date d = le.getAccessTime();
            String ip = le.getIpAddress();
            String ds = d.toString();
            String[] splitted = ds.split("\\s+");
            String monthDay = splitted[1] + " " + splitted[2];
            if (!dayIps.containsKey(monthDay)){
                dayIps.put(monthDay, new ArrayList<String>());
                dayIps.get(monthDay).add(ip);
            } else {
                dayIps.get(monthDay).add(ip);
            }
        }
        return dayIps;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDays){
        String dayWithMostIpsVisits = "";
        int mostIpsVisits = -1;
        for (String key: ipsForDays.keySet()){
            if (ipsForDays.get(key).size() > mostIpsVisits){
                mostIpsVisits = ipsForDays.get(key).size();
                dayWithMostIpsVisits = key;
            }
        }
        return dayWithMostIpsVisits;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDays, String day){
        ArrayList<String> ipAddresses = new ArrayList<String>();
        ArrayList<String> ipsForOneDay = ipsForDays.get(day);
        
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (int i = 0; i < ipsForOneDay.size(); i++){
            if (!counts.containsKey(ipsForOneDay.get(i))){
                counts.put(ipsForOneDay.get(i), 1);
            } else {
                counts.put(ipsForOneDay.get(i), counts.get(ipsForOneDay.get(i)) + 1);
            }
        }
        int mostVisitsByIp = mostNumberVisitsByIP(counts);
        
        for (String key: counts.keySet()){
            if (!ipAddresses.contains(key) && counts.get(key) == mostVisitsByIp){
                ipAddresses.add(key);
            }
        }
        return ipAddresses;
    }
}
