package DNA;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of DNAFrames here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class DNAFrames {
    private HashMap<String, Integer> codonCounts; 
    
    public DNAFrames(){
        codonCounts = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        codonCounts.clear();
        for (int i = start; i <= dna.length() - 3; i += 3){
            String codon = dna.substring(i, i+3);
            codon = codon.toUpperCase();
            if (!codonCounts.containsKey(codon)){
                codonCounts.put(codon, 1);
            } else {
                codonCounts.put(codon, codonCounts.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String largestCountElement = "";
        int largestCount = -1;
        for (String key: codonCounts.keySet()){
            if (codonCounts.get(key) > largestCount){
                largestCountElement = key;
                largestCount = codonCounts.get(key);
            }
        }
        return largestCountElement;
    }
    
    public void printCodonCounts(int start, int end){
        for (String key: codonCounts.keySet()){
            int c = codonCounts.get(key);
            if (c >= start && c <= end){
                System.out.println(key + " " + c);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = "";
        for (String line: fr.lines()){
            dna += line;
        }
        for (int i = 0; i < 3; i++){
            buildCodonMap(i, dna);
            System.out.println("Size of hashmap: " + codonCounts.size());
            String mostCommon = getMostCommonCodon();
            System.out.println("Most common: " + mostCommon + " " + codonCounts.get(mostCommon));
            printCodonCounts(1, 12);
        }
    }
}
