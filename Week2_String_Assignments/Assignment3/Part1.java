
/**
 * Coursera Course: Java Programming: Solving Problems with Software. 
 * Week 2 
 * Assignment 3: Find all genes in DNA strand.
 * Part 1 (there is noly one part in Assignment 3)
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
    
    public String findGene(String dna, int whereStart){

        int startIndex = 0;
        int stopIndexFinal = 0;
        int minIndex = 0;        
        
        while (true){
            
            startIndex = dna.indexOf("ATG", whereStart);  
            
            if (startIndex == -1){return "";} // if there is no start codon ATG, return empty string 
            
            int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            int stopIndexTGA = findStopCodon(dna, startIndex, "TGA"); 
            
            minIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA)); //find the location of closest stop colon
           
            if (minIndex == dna.length()){
                whereStart = startIndex +3; //if there is no valid stop codon for the current ATG, search for the next ATG
            }
            
            else{
                return dna.substring(startIndex, minIndex + 3); //return the gene found
            }
        }                   
    }
    
    public void printAllGenes(String dna){
            
        int startIndex = 0;
        int count = 0;
            
        while (true){
            String gene = findGene(dna, startIndex);
                
            if (gene == ""){
                    break;
            }   
            System.out.println("Found a valid gene: " + gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            count ++;
        }
        
        System.out.println("Total number of genes found: " + count);            
        System.out.println("Gene search finished. All valid genes are printed. Check your DNA if there aren't any genes printed.");
    }  
    
    public StorageResource getAllGenes(String dna){
            
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        
        while (true){
            
            String gene = findGene(dna, startIndex);
            
            if (gene == ""){
                    break;
            }

            sr.add(gene); //add the found gene to the list
            startIndex = dna.indexOf(gene, startIndex) + gene.length(); // move on to try to find another gene         
        }
        
        return sr; //work completes, return the list of all genes
    }  
    
    public float cgRatio(String dna){ //this method aims to find the ratio of C's and G's in DNA strand
        
        int currIndexC = -1;
        int currIndexG = -1;
        int count = 0;
        
        while(true){
            currIndexC = dna.indexOf("C", currIndexC+1);
            if (currIndexC == -1){break;}
            count ++;
        }
        
        while(true){
            currIndexG = dna.indexOf("G", currIndexG+1);
            if (currIndexG == -1){break;}
            count ++;
        }
        
        return (float)count/dna.length();
    }
    
    public int countCTG(String dna){ //this method aims to find the amounts ATG's in DNA strand
        
        int currindex = -3;
        int count = 0;
        while (true){
            currindex = dna.indexOf("CTG", currindex+3);
            if (currindex == -1){break;}
            count ++;
        }
        return count;
    }
    
    public void processGenes(StorageResource sr){ //this method aims to process the gene list. This method can be customized for biology studies

        int countNumOfGenes = 0;
        int countLongString = 0;
        int countHighCGRatio = 0;
        int longestGeneLength = 0;
        int temp = 0;
        
        for (String gene : sr.data()){
            countNumOfGenes ++;
        }
        
        System.out.println("Total number of genes: " + countNumOfGenes);     
        
        for (String gene : sr.data()){
            if (gene.length() > 60){
                System.out.println("Found a gene that has length larger than 60: " + gene);
                countLongString ++;
            }
        }
        
        System.out.println("Total number of genes that have length larger than 60: " + countLongString);
        
        for (String gene : sr.data()){
            
            longestGeneLength = ((gene.length() > longestGeneLength) ? gene.length() : longestGeneLength);
            
            if (cgRatio(gene) > 0.35){
                System.out.println("Found a gene that has CG ratio larger than 0.35: " + gene);
                countHighCGRatio ++;
            }
        }
        
        System.out.println("Total number of genes that have CG ratio larger than 0.35: " + countHighCGRatio);     
        System.out.println("The length of the longest gene: " + longestGeneLength);      
        
    }
    
    public void testFindStopCodon(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTT";
        String dna2 = "AAAATGTTTGGGAAAAAAGGGTTT";
        String dna3 = "AAAATGTTTGGGAAATAGGGGTTT";
        String dna4 = "ATGCCTAACCTGACCCCTAGCCCCCCATGCCCTAA";        
        int startIndex = 0;
        Part1 test = new Part1();
        System.out.println(test.findStopCodon(dna1, startIndex, "TAA")); 
        System.out.println(test.findStopCodon(dna2, startIndex, "TAA")); 
        System.out.println(test.findStopCodon(dna3, startIndex, "TAG")); 
        System.out.println(test.findStopCodon(dna4, startIndex, "TAG"));             
    }
    
    public void testFindGene(){
        String dna9 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";
        System.out.println("DNA string is: " + dna9);
        System.out.println("Valid gene from above DNA is: " + findGene(dna9, 0));         
    }
    
    public void testprintAllGenes(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";
        printAllGenes(dna1);        
    }
    
    public void testgetAllGenes(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";
        StorageResource sr = getAllGenes(dna1);
        for (String dna : sr.data()){
            System.out.println("Found a valid gene: " + dna);
        }
    } 
    
    public void testcgRatio(){
        String dna1 = "GGAATTAAGGCCAACC";
        System.out.println("CG ratio is: " + cgRatio(dna1));        
    }
    
    public void testcountCTG(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";
        System.out.println("CTG count is: " + countCTG(dna1));        
    }
    
    public void testprocessGenes(){
        String dna1 = "AAAATGTTTGGGAAATAAGGGTTTATGTTTGGGTTTGGGAAAATGTAATTTGGGATGGGGAAATTTTGAGGG";        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString().toUpperCase();       
        processGenes(getAllGenes(dna1));  
    }
    
    public static void main (String[] args) {

    }
}
