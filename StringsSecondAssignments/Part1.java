package StringsSecondAssignments;


/**
 * Write a description of Part1 here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class Part1 {
    int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.length();  
        for (int i = startIndex; i <= dna.length() - 3; i += 3){
            String substr = dna.substring(i, i+3);
            
            if (substr.equals(stopCodon))
            {
                stopIndex = i;
            }
        }
        return stopIndex;
    }
    
    
    String findGene(String dna){
        // find index of the first occurence of start codon ATG
        int index_ATG = -1;
        for (int i = 0; i <= dna.length() - 3; i += 3){
            String substr = dna.substring(i, i+3);
            if (substr.equals("ATG")){
                index_ATG = i;
            }
        }
        
        if (index_ATG == -1){
            return "";
        }
        
        int index_TAA = findStopCodon(dna, index_ATG, "TAA");
        int index_TAG = findStopCodon(dna, index_ATG, "TAG");
        int index_TGA = findStopCodon(dna, index_ATG, "TGA");
        int closest = Math.min(index_TAA, index_TAG);
        int closest2 = Math.min(closest, index_TGA);
        
        if (closest2 == dna.length()){
            return "";
        }
        
        String finalGene = dna.substring(index_ATG, closest2 + 3);
        return finalGene;
        
    }
    
    
    void testFindStopCodon(){
        System.out.println("testFindStopCodon");
        
        int res1 = findStopCodon("ATGGTAAACGTCAGT", 0, "GTC");
        System.out.println("Res 1: " + res1);
        
        int res2 = findStopCodon("GTCATGAAACGT", 3, "CGT");
        System.out.println("Res 2: " + res2);
        
        int res3 = findStopCodon("ATGGTA", 0, "GTA");
        System.out.println("Res 3: " + res3);
        
        int res4 = findStopCodon("ATG", 0, "GTA");
        System.out.println("Res4: " + res4);
    }
    
    
    void testFindGene(){
        
        System.out.println("testFindGene");
        
        // dna with no atg
        String res1 = findGene("GTATAGCGACCCAAA");
        System.out.println("Res 1: " + res1);
        
        // dna with atg and one stop codon
        String res2 = findGene("ATGCCCGGGTAAAAA");
        System.out.println("Res 2: " + res2);
        
        // dna with atg and multiple stop codons
        String res3 = findGene("GTAATGTAATAGAAAGTA");
        System.out.println("Res 3: " + res3);
        
        // dna with atg and no valid stop codon
        String res4 = findGene("ATCATGGTAGCCCGA");
        System.out.println("Res 4: " + res4);
        
    }
    
    void printAllGenes(String dna){
        int startIndex = 0;
        while (startIndex <= dna.length() - 3){
            int endIndex = startIndex;
            while (endIndex <= dna.length() - 1){
                String substr = dna.substring(startIndex, endIndex);
                String gene = findGene(substr);
                if (gene != ""){
                    System.out.println("Gene: " + gene);
                }
                endIndex += 3;
            }
            startIndex += 1;
            
        }
    }
}

