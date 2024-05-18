package LogsAndIPs;


/**
 * Write a description of class Tester here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        int unique = la.countUniqueIPs();
        System.out.println("Number of unique ip: " + unique + "\n");
        
        int exitCode = 400;
        System.out.println("Logs with exit code higher than " + exitCode);
        la.printAllHigherThanNum(exitCode);
        
        String day = "Sep 27";
        ArrayList<String> u = la.uniqueIPVisitsOnDay(day);
        System.out.println("Unique ip visits on day " + day + ": " + u.size() + "\n");
        
        int u2 = la.countUniqueIPsInRange(400, 499);
        System.out.println("Unique ip visits in range: " + u2 + "\n");
        
        HashMap<String, Integer> visitsPerIp = la.countVisitsPerIP();
        int mostVByIP = la.mostNumberVisitsByIP(visitsPerIp);
        System.out.println("Most visits by a single ip: " + mostVByIP + "\n");
        
        ArrayList<String> ipMostV = la.iPsMostVisits(visitsPerIp);      
        System.out.println("Ips with most visits: ");
        for (int i = 0; i < ipMostV.size(); i++){
            System.out.println(ipMostV.get(i));
        }
        System.out.println("\n");
        
        HashMap<String, ArrayList<String>> daysIps = la.iPsForDays();
        /*System.out.println("Days and ip on that day: ");
        for (String key: daysIps.keySet()){
            System.out.println("Day: " + key);
            for (int i = 0; i < daysIps.get(key).size(); i++){
                System.out.println(daysIps.get(key).get(i));
            }
        }*/
        System.out.println("\n");
        
        String dayMostVisits = la.dayWithMostIPVisits(daysIps);
        System.out.println("Day with most ips visits: " + dayMostVisits + "\n");
        
        String day2 = "Sep 30";
        ArrayList<String> ipsMostVisitsForDay = la.iPsWithMostVisitsOnDay(daysIps, day2);
        System.out.println("Ips with most visits for day " + day2 + ": ");
        for (int i = 0; i < ipsMostVisitsForDay.size(); i++){
            System.out.println(ipsMostVisitsForDay.get(i));
        }
        System.out.println("\n");
    }
}
