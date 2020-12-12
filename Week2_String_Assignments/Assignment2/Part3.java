/**
 * Coursera Course: Java Programming: Solving Problems with Software. 
 * Week 2 
 * Assignment 2: Find all genes in DNA strand.
 * Part 3
 * 
 * @Zitao He
 * @version 1
 */

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){        
        //String dnaNew = dna.substring(startIndex);
        int stopIndex = dna.indexOf(stopCodon, startIndex);        
        // if there is no valid DNA, return the length of original dna strand.
        if (stopIndex == -1 || 
        (stopIndex != -1 && (stopIndex - startIndex) % 3 != 0))
        {
            return dna.length();
        }
        
        return stopIndex;
    }
    
    public String findGene(String dna){
        
        String startCodon = "ATG";

        int startIndex = 0;
        int stopIndexTAA = 0;
        int stopIndexTAG = 0;
        int stopIndexTGA = 0;
        int stopIndexFinal = 0;
        startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){return "";}
        
        stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
        stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
        stopIndexTGA = findStopCodon(dna, startIndex, "TGA"); 
       
        //If there is no valid stop codon, return an empty string
        if (stopIndexTAA == dna.length() && stopIndexTAG == dna.length() && stopIndexTGA == dna.length()){return "";}
        
        //Find the stop codon that is closest to the start codon
        int[] stopIndexList = {stopIndexTAA, stopIndexTAG, stopIndexTGA};
        int temp = dna.length();        
        for (int index : stopIndexList){
            if (index != -1){temp = Math.min(temp, index);}
        }
        stopIndexFinal = temp;
        
        return dna.substring(startIndex, stopIndexFinal + 3);
    }
    
    public void printAllGenes(String dna){
            
        int startIndex = 0;
        int newStartIndex = 0;
            
        while (true){
            String gene = findGene(dna);
                
            if (gene.isEmpty()){
                    break;
            }
                
            System.out.println("Found a valid gene: " + gene);
            newStartIndex = dna.indexOf(gene) + gene.length();
            dna = dna.substring(newStartIndex);
        }
            
        System.out.println("Gene search finished. All valid genes are printed. Check your DNA if there aren't any genes printed.");
    }    
       
   public int countGenes(String dna){
        int startIndex = 0;
        int newStartIndex = 0;
        int count = 0;
            
        while (true){
            String gene = findGene(dna);
                
            if (gene.isEmpty()){
                    break;
            }
                
            newStartIndex = dna.indexOf(gene) + gene.length();
            dna = dna.substring(newStartIndex);
            count ++;
        }
        return count;
               
   }
    
   public void testCountGenes(){
       String dna = "ATGTAAGATGCCCTAGT";
       System.out.println(countGenes(dna));
   }
}
