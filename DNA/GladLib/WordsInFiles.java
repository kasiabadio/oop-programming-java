package GladLib;
import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsFiles;
    
    public WordsInFiles(){
        wordsFiles = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word: fr.words()){
            if (!wordsFiles.containsKey(word)){
                wordsFiles.put(word, new ArrayList<>());
                wordsFiles.get(word).add(f.getName());
            } else {
                if (!wordsFiles.get(word).contains(f.getName())){
                    wordsFiles.get(word).add(f.getName());
                }
            }
        }
    }
    
    private void buildWordFileMap(){
        if (wordsFiles.size() > 0){
            wordsFiles.clear();
        }
        DirectoryResource dr = new DirectoryResource();
        for (File file: dr.selectedFiles()){
            addWordsFromFile(file);
        }
    }
    
    private int maxNumber(){
        int maxNumberOfFiles = -1;
        for (String key: wordsFiles.keySet()){
            int numberOfFiles = wordsFiles.get(key).size();
            if (numberOfFiles > maxNumberOfFiles){
                maxNumberOfFiles = numberOfFiles;
            }
        }
        return maxNumberOfFiles;
    }
    
    private ArrayList wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String key: wordsFiles.keySet()){
            int numberOfFiles = wordsFiles.get(key).size();
            if (numberOfFiles == number){
                words.add(key);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word){
        ArrayList<String> files = wordsFiles.get(word);
        for (int i = 0; i < files.size(); i++){
            System.out.println(files.get(i));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("Max number of files: " + maxNum);
        
        int numFiles = 7;
        ArrayList words = wordsInNumFiles(numFiles);
        System.out.println("Number of words that each occur in " + numFiles + " files: " + words.size());
        
        int numFiles2 = 4;
        ArrayList words2 = wordsInNumFiles(numFiles2);
        System.out.println("Number of words that each occur in " + numFiles2 + " files: " + words2.size());
        //for (int i = 0; i < words.size(); i++){
        //    System.out.println(words.get(i));
        //}
        printFilesIn("tree");
        
    }
}
