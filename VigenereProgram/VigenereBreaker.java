package VigenereProgram;

import java.util.*;
import edu.duke.*;

/**
 * Write a description of VigenereBreaker here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.1
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
        
        FileResource fr = new FileResource();
        String contents = "";
        for (String line: fr.lines()){
            contents += line + " ";
        }
        
        String[] dictionaries = {"Danish", "Dutch", "English", "French", 
            "German", "Italian", "Portugese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (int i = 0; i < dictionaries.length; i++){
            System.out.println("Dictionary language: " + dictionaries[i]);
            FileResource fr1 = new FileResource();
            HashSet<String> words = readDictionary(fr1);
            languages.put(dictionaries[i], words);
        }
        
        breakForAllLangs(contents, languages);
    
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
            char c = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, i, c);
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
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> countsChar = new HashMap<Character, Integer>();
        for (String val: dictionary){
            for (int i = 0; i < val.length(); i++){
                char c = val.charAt(i);
                if (!countsChar.containsKey(c)){
                    countsChar.put(c, 1);
                } else {
                    countsChar.put(c, countsChar.get(c) + 1);
                }
            }
        }
        
        char mostFrequent = '-';
        int countMostFrequent = -1;
        for (char key: countsChar.keySet()){
            int c = countsChar.get(key);
            if (c > countMostFrequent){
                countMostFrequent = c;
                mostFrequent = key;
            }
        }
        return mostFrequent;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String d = "";
        int maxNumOfWords = -1;
        String l = "";
        for (String key: languages.keySet()){
            System.out.println("Language: " + key);
            HashSet<String> words = languages.get(key);
            String decrypted = breakForLanguage(encrypted, words);
            int numOfWords = countWords(decrypted, words);
            if (maxNumOfWords < numOfWords){
                maxNumOfWords = numOfWords;
                d = decrypted;
                l = key;
            }
        }
        if (d.length() < 100){
            System.out.println(d);
        } else {
            System.out.println(d.substring(0, 100));
        }
        System.out.println("Language: " + l);
    }
    
}
