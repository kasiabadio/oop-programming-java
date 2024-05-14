package GladLib;

import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> wordsHaveBeenSeen;
    
    private int numberOfWordsReplaced;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordsHaveBeenSeen = new ArrayList<String>();
        numberOfWordsReplaced = 0;
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
        wordsHaveBeenSeen = new ArrayList<String>();
        numberOfWordsReplaced = 0;
    }
    
    private void initializeFromSource(String source) {
        adjectiveList = readIt();    
        nounList = readIt();
        colorList = readIt();
        countryList = readIt();
        nameList = readIt();        
        animalList = readIt();
        timeList = readIt();
        verbList = readIt();
        fruitList = readIt();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
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
    
    private ArrayList<String> readIt(){
        ArrayList<String> list = new ArrayList<String>();
        
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            list.add(line);
        }
        
        return list;
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
