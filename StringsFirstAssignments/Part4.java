package StringsFirstAssignments;
import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class Part4 {
    void checkResource(){
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s: resource.words()){
            String s2 = s.toLowerCase();
            System.out.println(s2);
            int firstIndex = s2.indexOf("youtube.com");
            int secondIndex = firstIndex + 11;
            System.out.println(firstIndex);
            if (firstIndex != -1){
                System.out.println("HELLO");
                String first_substr = s2.substring(0, firstIndex);
                String second_substr = s2.substring(secondIndex, s.length());
                int firstIndexBackslash = first_substr.indexOf("\"");
                int secondIndexBackslash = secondIndex + second_substr.indexOf("\"");
                String substring = s.substring(firstIndexBackslash, secondIndexBackslash);
                System.out.println(substring);
            }
        }
    }
}
