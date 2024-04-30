package BabiesNames;
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
    
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " + rec.get(0) +
                                    " Gender " + rec.get(1) +
                                    " Num born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int femalesCount = 0;
        int malesCount = 0;
        int numberOfGirlsNames = 0;
        int numberOfBoysNames = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            String maleFemale = rec.get(1);
            if (maleFemale.equals("F")){
                femalesCount += numBorn;
                numberOfGirlsNames += 1;
            } else if (maleFemale.equals("M")){
                malesCount += numBorn;
                numberOfBoysNames += 1;
            }
            totalBirths += numBorn;
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Females births = " + femalesCount);
        System.out.println("Males births = " + malesCount);
        System.out.println("Number of girls names = " + numberOfGirlsNames);
        System.out.println("Number of boys names = " + numberOfBoysNames);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(String name, String gender, FileResource fr){
        int rank = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            String maleFemale = rec.get(1);
            if (gender.equals(maleFemale)){
                String nameFromFile = rec.get(0);
                rank += 1;
                if (nameFromFile.equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank(String name, String gender, int year){
        FileResource fr = new FileResource();
        int rank = getRank(name, gender, fr);
        System.out.println("Rank of name " + name + " in year " + year + " is " + rank);
    }
    
    public String getName(int rank, String gender, FileResource fr){
        int rankCurrent = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            String maleFemale = rec.get(1);
            if (gender.equals(maleFemale)){
                rankCurrent += 1;
                if (rank == rankCurrent){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(int rank, String gender, int year){
        FileResource fr = new FileResource();
        String name = getName(rank, gender, fr);
        System.out.println("Name of girl of rank " + rank + " is " + name + " in " + year);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource();
        int rank = getRank(name, gender, fr);
        
        FileResource fr2 = new FileResource();
        String name2 = getName(rank, gender, fr2);
        System.out.println(name + " born in " + year + " would be " + name2 + " if she was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 1000000000;
        String yearHighestRank = "";
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int rank = getRank(name, gender, fr);
            String fileName = f.getName();
            String year = "";
            for (int i = 0; i < fileName.length(); i++){
                char c = fileName.charAt(i);
                if (Character.isDigit(c)){
                    year += c;
                }
                if (year.length() == 4){
                    break;
                }
            }
            if (highestRank == 1000000000 && rank > -1){
                highestRank = rank;
                yearHighestRank = year;
            } else if (rank < highestRank && rank > -1){
                highestRank = rank;
                yearHighestRank = year;
            }
        }
        int yearOfHighestRank = Integer.parseInt(yearHighestRank);
        return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender){
        double averageRank = 0;
        double sumRank = 0;
        int numRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int rank = getRank(name, gender, fr);
            sumRank += (double) rank;
            numRank += 1;
        }
        if (sumRank == 0){
             return -1.0;
        }
        averageRank = sumRank / numRank;
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender){
        FileResource fr = new FileResource();
        int rankName = getRank(name, gender, fr);
        int totalBirthsRankedHigherThanName = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            String maleFemale = rec.get(1);
            String name2 = rec.get(0);
            int births = Integer.parseInt(rec.get(2));
            if (gender.equals(maleFemale)){
                int rank = getRank(name2, gender, fr);
                if (rank < rankName){
                    totalBirthsRankedHigherThanName += births;
                }
            }
        }
        return totalBirthsRankedHigherThanName;
    }
}
