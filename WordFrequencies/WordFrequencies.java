package WordFrequencies;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of WordFrequencies here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word: fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " "  +  myFreqs.get(i));
        }
        int maxIndex = findIndexOfMax();
        
        System.out.println("Number of unique words: " + myWords.size());
        System.out.println("The word that occurs most often and its counts are: " +myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
    
    public int findIndexOfMax(){
        int iLargest = -1;
        for (int i = 0; i < myWords.size(); i++){
            if (i == 0){
                iLargest = 0;
            } else if (myFreqs.get(i) > myFreqs.get(iLargest)){
                iLargest = i;
            }
        }
        return iLargest;
    }
    
}
