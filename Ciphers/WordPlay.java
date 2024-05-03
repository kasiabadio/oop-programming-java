package Ciphers;
import edu.duke.*;

/**
 * Write a description of WordPlay here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class WordPlay {
    
    public boolean isVowel(char ch){
        String vowelsUpper = "AEIOU";
        String vowelsLower = "aeiou";
        for (int i = 0; i < vowelsUpper.length(); i++){
            char currentUpper = vowelsUpper.charAt(i);
            char currentLower = vowelsLower.charAt(i);
            if (currentUpper == ch || currentLower == ch){
                return true;
            }
            
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch){
        String newPhrase = "";
        for (int i = 0; i < phrase.length(); i++){
            boolean isVowel = isVowel(phrase.charAt(i));
            if (isVowel == true){
                newPhrase += '*';
            } else {
                newPhrase += phrase.charAt(i);
            }
        }
        return newPhrase;
    }
    
    public String emphasize(String phrase, char ch){
        String newPhrase = "";
        for (int i = 0; i < phrase.length(); i++){
            if (phrase.charAt(i) == ch){
                if ((i+1) % 2 != 0){
                    newPhrase += "*";
                } else if ((i+1) % 2 == 0){
                    newPhrase += "+";
                }
            } else {
                newPhrase += phrase.charAt(i);
            }
        }
        return newPhrase;
    }
    
}
