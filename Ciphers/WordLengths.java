package Ciphers;
import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word: resource.words()){
            word = word.toLowerCase();
            if (Character.isLetter(word.charAt(word.length() - 1)) == false){
                word = word.substring(0, word.length() - 1);
            }
            if (Character.isLetter(word.charAt(0)) == false){
                word = word.substring(1, word.length());
            }
            
            int length = word.length();
            if (length < counts.length - 1){
                counts[length] += 1;
            } else if (length >= counts.length - 1){
                counts[counts.length - 1] += 1;
            }          
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++){
            System.out.println("Length: " + i + " number of words: " + counts[i]);
        }
    }
    
}
