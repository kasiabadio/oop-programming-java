package CiphersOOP;
import edu.duke.*;

/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class TestCaesarCipherTwo {
    private int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] frequencies){
        int maxValue = 0;
        int maxI = -1;
        for (int i = 0; i < frequencies.length; i++){
            if (i == 0){
                maxValue = frequencies[i];
                maxI = i;
            } else if (frequencies[i] > maxValue){
                maxValue = frequencies[i];
                maxI = i;
            }
        }
        return maxI;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String s = "";
        for (String line: fr.lines()){
            s += line;
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        
        System.out.println("Encrypted: ");
        String encrypted = cc.encrypt(s);
        System.out.println(encrypted);
        
        System.out.println("Decrypted: ");
        String decrypted = cc.decrypt(s);
        System.out.println(decrypted);
        
        breakCaesarCipher(s);
    }
    
    public void breakCaesarCipher(String input){
        String messageOne = "";
        String messageTwo = "";
        for (int i = 0; i < input.length(); i++){
            if (i % 2 == 0){
                messageOne += input.charAt(i);;
            } else if (i % 2 != 0){
                messageTwo += input.charAt(i);
            }
        }
        
        int[] freqsOne = countLetters(messageOne);
        int maxDexOne = maxIndex(freqsOne);
        int decryptionKeyOne = maxDexOne - 4;
        if (maxDexOne < 4){
            decryptionKeyOne = 26 - (4 - maxDexOne);        
        }
        
        int[] freqsTwo = countLetters(messageTwo);
        int maxDexTwo = maxIndex(freqsTwo);
        int decryptionKeyTwo = maxDexTwo - 4;
        if (maxDexTwo < 4){
            decryptionKeyTwo = 26 - (4 - maxDexTwo);        
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(decryptionKeyOne, decryptionKeyTwo);
        System.out.println("Decryption key 1: " + decryptionKeyOne + " decryption key 2: " + decryptionKeyTwo);
        String decrypted = cc.decrypt(input);
        System.out.println("Decrypted 2: " + decrypted);
    }
    
}
