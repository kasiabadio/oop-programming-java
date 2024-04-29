package ParsingWeatherData;
import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestTemp = null;
        float coldestTempF = 200;
        for (CSVRecord r : parser){
            String temp = r.get("TemperatureF");
            float tempConverted = (float) Double.parseDouble(temp);
            if (coldestTemp == null){
                coldestTempF = tempConverted;
                coldestTemp = r;
            } else {
                if (tempConverted < coldestTempF){
                    coldestTempF = tempConverted;
                    coldestTemp = r;
                }
            }
        }
        return coldestTemp;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        String temperature = coldestHour.get("TemperatureF");
        String time = coldestHour.get("TimeEDT");
        System.out.println("Time: " + time + " Temperature: " + temperature);
    }
    
    public void fileWithColdestTemperature(){
        CSVRecord coldestTemp = null;
        double coldestTempD = 200;
        String name = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestTempFromFile = coldestHourInFile(parser);
            double coldestTempFromFileD = (float) Double.parseDouble(coldestTempFromFile.get("TemperatureF"));
            if (coldestTemp == null){
                coldestTemp = coldestTempFromFile;
                coldestTempD = coldestTempFromFileD;
                try {
                    name = f.getCanonicalPath();
                } catch (Exception e) {
                    System.out.println("Can't assign path");
                }
            } else {
                if (coldestTempFromFileD < coldestTempD){
                    coldestTemp = coldestTempFromFile;
                    coldestTempD = coldestTempFromFileD;
                    try {
                        name = f.getCanonicalPath();
                    } catch (Exception e) {
                        System.out.println("Can't assign path");
                    }
                }
            }
        }
        String temperature = coldestTemp.get("TemperatureF");
        System.out.println("Coldest day was in file " + name);
        System.out.println("Coldest temperature on that day was " + temperature);
        
        System.out.println("All the Temperatures on the coldest day were: ");
        FileResource fr = new FileResource(name);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord r: parser){
            String date = r.get("DateUTC");
            String temp = r.get("TemperatureF");
            System.out.println(date + " " + temp);
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        float lowestHumidityF = 200;
        for (CSVRecord r : parser){
            String humidity = r.get("Humidity");
            if (humidity.equals("N/A")){
                assert true;
            } else {
                float humidityConverted = (float) Double.parseDouble(humidity);
                if (lowestHumidity == null){
                    lowestHumidityF = humidityConverted;
                    lowestHumidity = r;
                } else {
                    if (humidityConverted < lowestHumidityF){
                        lowestHumidityF = humidityConverted;
                        lowestHumidity = r;
                    }
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String humidity = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + time);
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = null;
        double lowestHumidityD = 200;
        String name = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestHumidityFromFile = lowestHumidityInFile(parser);
            double lowestHumidityFromFileD = (float) Double.parseDouble(lowestHumidityFromFile.get("Humidity"));
            if (lowestHumidity == null){
                lowestHumidity = lowestHumidityFromFile;
                lowestHumidityD = lowestHumidityFromFileD;
            } else {
                if (lowestHumidityFromFileD < lowestHumidityD){
                    lowestHumidity = lowestHumidityFromFile;
                    lowestHumidityD = lowestHumidityFromFileD;
                }
            }
        }
        
        return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        String humidity = lowestHumidity.get("Humidity");
        String time = lowestHumidity.get("DateUTC");
        System.out.println("Lowest humidity was " + humidity + " at " + time);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double averageTemp = 0;
        double sumTemp = 0;
        int numberOfTemp = 0;
        for (CSVRecord r: parser){
            String humidity = r.get("Humidity");
            double humidityD = (double) Double.parseDouble(humidity);
            if (humidityD >= value){
                String temp = r.get("TemperatureF");
                double tempD = (double) Double.parseDouble(temp);
                sumTemp += tempD;
                numberOfTemp += 1;
            }
        }
        if (numberOfTemp > 0){
            averageTemp = sumTemp / numberOfTemp;
        }
        return averageTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureWithHighHumidityInFile(parser, 30);
        if (temp == 0){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature: " + temp);
        }
    }
}
