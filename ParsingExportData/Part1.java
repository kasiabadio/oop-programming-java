package ParsingExportData;
import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //String country = countryInfo(parser, "Germany");
        //System.out.println(country);
        
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        //int numOfExporters = numberOfExporters(parser, "cocoa");
        //System.out.println(numOfExporters);
        
        bigExporters(parser, "$999,999,999,999");
        
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        String s = "";
        for (CSVRecord row : parser){
            String c = row.get("Country");
            if (c.contains(country)){
                String e = row.get("Exports");
                String v = row.get("Value (dollars)");
                s = c + ": " + e + ": " + v;
                return s;
            }
            
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord row : parser){
            String e = row.get("Exports");
            if (e.contains(exportItem1) && e.contains(exportItem2)){
                String c = row.get("Country");
                String v = row.get("Value (dollars)");
                System.out.println(c);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int numberOfCountries = 0;
        for (CSVRecord row: parser){
            String e = row.get("Exports");
            if (e.contains(exportItem)){
                numberOfCountries += 1;
            }
        }
        return numberOfCountries;
    }
    
    void bigExporters(CSVParser parser, String amount){
        for (CSVRecord row: parser){
            String v = row.get("Value (dollars)");
            
            if (v.length() > amount.length()){
                String c = row.get("Country");
                System.out.println(c + " " + v);
            }
                
        }
    }
}
