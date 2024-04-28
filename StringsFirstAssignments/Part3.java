package StringsFirstAssignments;


/**
 * Write a description of Part3 here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class Part3 {
    boolean twoOccurrences(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        int length = stringa.length();
        int endIndex = startIndex + length;
        
        String substrStringb = stringb.substring(endIndex, stringb.length());
      
        int startIndex2 = substrStringb.indexOf(stringa);
        if (startIndex2 == -1){
            return false;
        }
        return true;
    }
    
    void testing(){
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
    
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }

    String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            return stringb;
        }
        int length = stringa.length();
        int endIndex = startIndex + length;
        
        String substrStringb = stringb.substring(endIndex, stringb.length());
        return substrStringb;
    }
}
