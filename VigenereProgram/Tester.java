package VigenereProgram;
import edu.duke.*;
import java.util.*;

/**
 * Write a description of Tester here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(6);
        FileResource fr = new FileResource();
        String text = "";
        for (String line: fr.lines()){
            text += line + " ";
        }
        String encrypted = cc.encrypt(text);
        System.out.println("Encrypted: ");
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: ");
        System.out.println(decrypted);
    }
    
    public void testCaesarCracker(){
        // english
        CaesarCracker cc1 = new CaesarCracker();
        FileResource fr1 = new FileResource();
        String text1 = "";
        for (String line: fr1.lines()){
            text1 += line + " ";
        }
        String decrypted1 = cc1.decrypt(text1);
        System.out.println(decrypted1);
        
        // portugese
        CaesarCracker cc2 = new CaesarCracker('a');
        FileResource fr2 = new FileResource();
        String text2 = "";
        for (String line: fr2.lines()){
            text2 += line + " ";
        }
        String decrypted2 = cc2.decrypt(text2);
        System.out.println(decrypted2);
    }
    
    public void testVigenereCipher(){
        int [] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String text = "";
        for (String line: fr.lines()){
            text += line + " ";
        }
        String encrypted = vc.encrypt(text);
        System.out.println("Encrypted: ");
        System.out.println(encrypted);
        
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted: ");
        System.out.println(decrypted);
    }
    
    public void testVigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        String s = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println(s);
        
        FileResource fr = new FileResource();
        String text = "";
        for (String line: fr.lines()){
            text += line + " ";
        }
        
        int[] keys = vb.tryKeyLength(text, 5, 'e');
        System.out.println(Arrays.toString(keys));
        
        
        
    }
}
