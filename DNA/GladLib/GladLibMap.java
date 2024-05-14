package GladLib;

import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordsHaveBeenSeen;
    private ArrayList<String> categoriesUsed;
    
    private int numberOfWordsReplaced;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordsHaveBeenSeen = new ArrayList<String>();
        categoriesUsed = new ArrayList<String>();
        numberOfWordsReplaced = 0;
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        wordsHaveBeenSeen = new ArrayList<String>();
        categoriesUsed = new ArrayList<String>();
        numberOfWordsReplaced = 0;
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country",
                        "name", "animal", "time", "verb", "fruit"};
        for (int i = 0; i < categories.length; i++){
            FileResource fr = new FileResource();
            if (!myMap.containsKey(categories[i])){
                myMap.put(categories[i], new ArrayList<String>());
            } 
            for (String word: fr.words()){
                if (!myMap.get(categories[i]).contains(word)){
                    myMap.get(categories[i]).add(word);
                }
            }
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        
        if (!categoriesUsed.contains(w.substring(first+1,last))){
            categoriesUsed.add(w.substring(first+1, last));
        }
        
        String sub = getSubstitute(w.substring(first+1,last));
        while (wordsHaveBeenSeen.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        numberOfWordsReplaced += 1;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(){
        String story = "";
        FileResource resource = new FileResource();
        for(String word : resource.words()){
            story = story + processWord(word) + " ";
        }
        
        return story;
    }
    
    private int totalWordsInMap(){
        int totalWords = 0;
        for (String key: myMap.keySet()){
            int arrayLength = myMap.get(key).size();
            totalWords += arrayLength;
        }
        return totalWords;
    }
    
    private int totalWordsConsidered(){
        int totalWords = 0;
        for (int i = 0; i < categoriesUsed.size(); i++){
            int arrayLength = myMap.get(categoriesUsed.get(i)).size();
            totalWords += arrayLength;
        }
        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        
        if (wordsHaveBeenSeen.size() > 0){
            wordsHaveBeenSeen.clear();
        }
        
        String story = fromTemplate();
        printOut(story, 60);
        System.out.printf("%n");
        System.out.println("Number of words replaced: " + numberOfWordsReplaced);
    }

}
