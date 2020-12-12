/**
 * Coursera Course: Java Programming: Solving Problems with Software. 
 * Week 2 
 * Assignment 2: Find all genes in DNA strand.
 * Part 2
 * 
 * @Zitao He
 * @version 1
 */

public class Part2 {
    public int howMany(String stringa, String stringb){
        
        int count = 0;
        int currStringaIndex = 0;
        int startIndex = 0;
        String restOfStringb = stringb;
        
        while (true){         
            currStringaIndex = stringb.indexOf(stringa, startIndex);             
            if (currStringaIndex == -1){break;}      
            startIndex = currStringaIndex + stringa.length();
            count ++;            
        }
        return count;
    }
    
    public void testHowMany(){
        String test1 = "ATGAACGAATTGAATC";        
        System.out.println(test1+"," + howMany("GAA", test1));       
    }
}
