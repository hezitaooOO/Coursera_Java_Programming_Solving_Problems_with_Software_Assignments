
/**
 * Write a description of Part1 here.
 * 
 * @Zitao He 
 * @Version 1
 */
public class Part1 {
    
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
                return "";
        }
          
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        
        if (stopIndex == -1){
                return "";
        }
        
        if ((stopIndex - startIndex) % 3 == 0){
                return dna.substring(startIndex, stopIndex + 3);
        }
        
        else{ 
            return "";
        }        
    }

    public void testSimpleGene(){
        //DNA1 no “ATG”
        String DNA1 = "CCCTTTATAAAGGG";
        //DNA2 no “TAA”
        String DNA2 = "GATGGTTCAACCCAATT";
        //DNA3 no “ATG” or “TAA”
        String DNA3 = "AGGCTGATTCGTAGCT";
        //DNA4 has both "ATG", "TAA" and between them has a multiple of 3
        String DNA4 = "CCGTATGGTTGCAGAGTAA";
        //DNA5 has both "ATG", "TAA" and between them has NOT multiple of 3
        String DNA5 = "CATGCTCGATAAGAGCTCTAGATCGAT";
        
        System.out.println("DNA string: " + DNA1);
        System.out.println("Gene string: " + findSimpleGene(DNA1)); 
        
        System.out.println("DNA string: " + DNA2);
        System.out.println("Gene string: " + findSimpleGene(DNA2));         
        
        System.out.println("DNA string: " + DNA3);        
        System.out.println("Gene string: " + findSimpleGene(DNA3));         
        
        System.out.println("DNA string: " + DNA4);        
        System.out.println("Gene string: " + findSimpleGene(DNA4));         
        
        System.out.println("DNA string: " + DNA5);
        System.out.println("Gene string: " + findSimpleGene(DNA5));         
    }

}
