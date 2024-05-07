package CiphersOOP;
import edu.duke.*;

/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class TestCaesarCipher {
    
    public int[] countLetters(String message){
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
    
    public int maxIndex(int[] frequencies){
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
        CaesarCipher cc = new CaesarCipher(18);
        
        System.out.println("Encrypted: ");
        String encrypted = cc.encrypt(s);
        System.out.println(encrypted);
        
        System.out.println("Decrypted: ");
        String decrypted = cc.decrypt(s);
        System.out.println(decrypted);
        
        breakCaesarCipher(s);
    }
    
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int decryptionKey = maxDex - 4;
        if (maxDex < 4){
            decryptionKey = 26 - (4 - maxDex);        
        }
        System.out.println("Decryption key: " + decryptionKey);
        CaesarCipher cc = new CaesarCipher(26 - decryptionKey);
        String decrypted = cc.encrypt(input);
        System.out.println("Decrypted: " + decrypted);
    }
}
