package WordFrequencies;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterNamesCounts;
    
    public CharactersInPlay(){
        characterNames = new ArrayList<String>();
        characterNamesCounts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = characterNames.indexOf(person);
        if (index == -1){
            characterNames.add(person);
            characterNamesCounts.add(1);
        } else {
            int value = characterNamesCounts.get(index);
            characterNamesCounts.set(index, value+1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            if (line.length() > 0){
                int firstDotIndex = line.indexOf(".");
                if (firstDotIndex != -1){
                    String speakingPart = line.substring(0, firstDotIndex);
                    System.out.println(speakingPart);
                    update(speakingPart);
                }
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++){
                if (characterNamesCounts.get(i) > 10){
                    System.out.println(characterNames.get(i) + " " + characterNamesCounts.get(i));
                }
        }
        charactersWithNumParts(10, 15);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < characterNames.size(); i++){
            if (characterNamesCounts.get(i) >= num1 && characterNamesCounts.get(i) <= num2){
                System.out.println(characterNames.get(i));
            }
        }
    }
    
}
