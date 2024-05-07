package CiphersOOP;
import edu.duke.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class CaesarCipher {
    private String alphabetU;
    private String alphabetL;
    private String shiftedAlphabetU;
    private String shiftedAlphabetL;
    private int mainKey;
    public CaesarCipher(int key){
        alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetU = alphabetU.substring(key) + alphabetU.substring(0, key);
        
        alphabetL = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetL = alphabetL.substring(key) + alphabetL.substring(0, key);
        
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
}
