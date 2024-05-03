package Ciphers;
import edu.duke.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetU = alphabetU.substring(key) + alphabetU.substring(0, key);
        String shiftedAlphabetL = alphabetL.substring(key) + alphabetL.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int indexUpper = alphabetU.indexOf(currChar);
            if (indexUpper != -1){
                char newChar = shiftedAlphabetU.charAt(indexUpper);
                encrypted.setCharAt(i, newChar);
            } else {
                int indexLower = alphabetL.indexOf(currChar);
                if (indexLower != -1){
                    char newChar = shiftedAlphabetL.charAt(indexLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = "abcdefghijklmnopqrstuvwxyz";
        
        String shiftedAlphabetUeven = alphabetU.substring(key1) + alphabetU.substring(0, key1);
        String shiftedAlphabetLeven = alphabetL.substring(key1) + alphabetL.substring(0, key1);
        
        String shiftedAlphabetUodd = alphabetU.substring(key2) + alphabetU.substring(0, key2);
        String shiftedAlphabetLodd = alphabetL.substring(key2) + alphabetL.substring(0, key2);
        
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int indexUpper = alphabetU.indexOf(currChar);
            if (indexUpper != -1){
                if (i % 2 == 0){
                    char newChar = shiftedAlphabetUeven.charAt(indexUpper);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabetUodd.charAt(indexUpper);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int indexLower = alphabetL.indexOf(currChar);
                if (indexLower != -1){
                    if (i % 2 == 0){
                        char newChar = shiftedAlphabetLeven.charAt(indexLower);
                        encrypted.setCharAt(i, newChar);
                    } else {
                        char newChar = shiftedAlphabetLodd.charAt(indexLower);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 5;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
}
