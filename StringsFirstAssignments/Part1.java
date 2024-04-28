package StringsFirstAssignments;


/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part1 {
    
    String findSimpleGene(String dna){
        String dnaStr = "";
        int indexStart = dna.indexOf("ATG");
        if (indexStart == -1){
            return "";
        }
        
        int indexEnd = dna.indexOf("TAA");
        if (indexEnd == -1){
            return "";
        }
        
        dnaStr = dna.substring(indexStart, indexEnd);
        if (dnaStr.length() % 3 == 0){
            return dnaStr;
        }
        return "";
    }
    
    void testSimpleGene(){
        String dnaOne = "ATCGTATAA";
        System.out.println("Dna one: " + dnaOne);
        System.out.println(findSimpleGene(dnaOne));
        
        String dnaTwo = "ATGCGATA";
        System.out.println("Dna two: " + dnaTwo);
        System.out.println(findSimpleGene(dnaTwo));
        
        String dnaThree = "CGAGGTAG";
        System.out.println("Dna three: " + dnaThree);
        System.out.println(findSimpleGene(dnaThree));
        
        String dnaFour = "ATGGCCTAA";
        System.out.println("Dna four: " + dnaFour);
        System.out.println(findSimpleGene(dnaFour));
        
        String dnaFive = "ATGGCCTTAA";
        System.out.println("Dna five: " + dnaFive);
        System.out.println(findSimpleGene(dnaFive));
    }
}
