package VigenereProgram;

import java.util.*;
import edu.duke.*;

/**
 * Write a description of VigenereBreaker here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String finalMessage = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            finalMessage += message.charAt(i);
        }
        return finalMessage;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++){
            String s = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker();
            int k = cc.getKey(s);
            key[i] = k;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr1 = new FileResource();
        HashSet<String> words = readDictionary(fr1);
        
        FileResource fr2 = new FileResource();
        String contents2 = "";
        for (String line: fr2.lines()){
            contents2 += line + " ";
        }
        String decrypted2 = breakForLanguage(contents2, words);
        
        System.out.println("Decrypted: ");
        if (decrypted2.length() < 100){
            System.out.println(decrypted2);
        } else {
            System.out.println(decrypted2.substring(0, 100));
        }
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String word: fr.words()){
            word = word.toLowerCase();
            hs.add(word);
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for (int i = 0; i < words.length; i++){
            String wordLower = words[i].toLowerCase();
            if (dictionary.contains(wordLower)){
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String dec = "";
        int maxNumWords = -1;
        int maxKeysLength = -1;
        for (int i = 0; i < 100; i++){
            int[] keys = tryKeyLength(encrypted, i, 'e');
            if (keys.length > 0){
                VigenereCipher vc = new VigenereCipher(keys);
                String decrypted = vc.decrypt(encrypted);
                int howManyWords = countWords(decrypted, dictionary);
                if (howManyWords > maxNumWords){
                    maxNumWords = howManyWords;
                    dec = decrypted;
                    maxKeysLength = keys.length;
                }
            }
            
        }
        System.out.println("Keys length: " + maxKeysLength);
        System.out.println("Valid words: " + maxNumWords);
        return dec;
    }
}
