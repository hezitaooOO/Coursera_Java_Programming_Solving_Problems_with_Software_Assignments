/**
 * Coursera Course: Java Programming: Solving Problems with Software. 
 * Week 2 
 * Assignment 2: Find all genes in DNA strand.
 * Part 1
 * 
 * @Zitao He
 * @version 1
 */

import edu.duke.*;
import java.util.Arrays;

public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int stopIndex = dna.indexOf(stopCodon, startIndex);

        while (stopIndex != -1){
            
            if ((stopIndex - startIndex) % 3 == 0){
                return stopIndex; //a valid stop codon is found, return the index of this stop codon
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1); //no valid stop condon is found, keep searching
            }
        }
        
        return dna.length(); //search completes, zero valid stop colon is found, return DNA length
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
    
    public void testFindStopCodon(){

        String dna1 = "AAAATGTTTGGGAAATAAGGGTTT";
        String dna2 = "AAAATGTTTGGGAAAAAAGGGTTT";
        String dna3 = "AAAATGTTTGGGAAATAGGGGTTT";
        int startIndex = 0;
        Part1 test = new Part1();
        System.out.println(test.findStopCodon(dna1, startIndex, "TAA"));
        System.out.println(test.findStopCodon(dna2, startIndex, "TAA"));
        System.out.println(test.findStopCodon(dna3, startIndex, "TAG")); 
    
    }
    
    public void testFindGene(){

    }
    
    public void testprintAllGenes(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";
        System.out.println("DNA is: " + dna1);
        printAllGenes(dna1);
        
    }
        
    public static void main (String[] args) {

    }
}
