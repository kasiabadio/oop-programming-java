package StringsSecondAssignments;


/**
 * Write a description of Part2 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part2 {
    int howMany(String stringa, String stringb){
        int i = 0;
        int c = 0;
        while (i <= stringb.length() - stringa.length()){
            String substr = stringb.substring(i, i + stringa.length());
            if (substr.equals(stringa)){
                c += 1;
                i += stringa.length();
            } else {
                i += 1;
            }
        }
        return c;
    }
    
    void testHowMany(){
        int res1 = howMany("GAA","ATGAACGAATTGAATC");
        System.out.println("Res1: " + res1);
        
        int res2 = howMany("AA", "ATAAAA");
        System.out.println("Res2: " + res2);
    }
    
    
}
