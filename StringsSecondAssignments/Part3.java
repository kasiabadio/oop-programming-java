package StringsSecondAssignments;


/**
 * Write a description of Part3 here.
 * 
 * @author Katarzyna Badio 
 * @version 1.0.0
 */
public class Part3 {
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
    
    
    void printAllGenes(String dna){
        int startIndex = 0;
        while (startIndex <= dna.length() - 3){
            String substr = dna.substring(startIndex, dna.length() - 1);
            String gene = findGene(substr);
            if (gene != ""){
                System.out.println("Gene: " + gene);
                startIndex += gene.length();
            }else {
                   startIndex += 1;
            }
        }
    }
    
    int countGenes(String dna){
        int startIndex = 0;
        int counter = 0;
        while (startIndex <= dna.length() - 3){
            String substr = dna.substring(startIndex, dna.length() - 1);
            System.out.println(substr);
            String gene = findGene(substr);
            if (gene != ""){
                counter += 1;
                startIndex += gene.length();
                
            }else {
                startIndex += 1;
            }
        }
        return counter;
    }
    
    void testCountGenes(){
        int count1 = countGenes("ATGTAAGATGCCCTAGT");
        System.out.println(count1);
        
        printAllGenes("AATGCTAACTAGCTGACTAAT");
       
    }
    
}
