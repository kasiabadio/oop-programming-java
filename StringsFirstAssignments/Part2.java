package StringsFirstAssignments;


/**
 * Write a description of Part2 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Part2 {
    String findSimpleGene(String dna, String startCodon, String stopCodon){
        String dnaStr = "";
        int indexStart = dna.indexOf(startCodon);
        int indexStart2 = dna.indexOf(startCodon.toLowerCase());
        if (indexStart == -1 && indexStart2 == -1){
            return "";
        } else if (indexStart == -1){
            indexStart = indexStart2;
        }
    
        
        int indexEnd = dna.indexOf(stopCodon);
        int indexEnd2 = dna.indexOf(stopCodon.toLowerCase());
        if (indexEnd == -1 && indexEnd2 == -1){
            return "";
        } else if (indexEnd == -1){
            indexEnd = indexEnd2;
        }
        
        
        dnaStr = dna.substring(indexStart, indexEnd + 3);
        if (dnaStr.length() % 3 == 0){
            return dnaStr;
        }
        return "";
    }
    
    void testSimpleGene(){
        String dnaOne = "ATCGTATAA";
        System.out.println("Dna one: " + dnaOne);
        System.out.println(findSimpleGene(dnaOne, "ATG", "TAA"));
        
        String dnaTwo = "ATGCGATA";
        System.out.println("Dna two: " + dnaTwo);
        System.out.println(findSimpleGene(dnaTwo, "ATG", "TAA"));
        
        String dnaThree = "CGAGGTAG";
        System.out.println("Dna three: " + dnaThree);
        System.out.println(findSimpleGene(dnaThree, "ATG", "TAA"));
        
        String dnaFour = "ATGGCCTAA";
        System.out.println("Dna four: " + dnaFour);
        System.out.println(findSimpleGene(dnaFour, "ATG", "TAA"));
        
        String dnaFive = "ATGGCCTTAA";
        System.out.println("Dna five: " + dnaFive);
        System.out.println(findSimpleGene(dnaFive, "ATG", "TAA"));
        
        String dnaSix = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("Dna six: " + dnaSix);
        System.out.println(findSimpleGene(dnaSix, "ATG", "TAA"));
    }
}
