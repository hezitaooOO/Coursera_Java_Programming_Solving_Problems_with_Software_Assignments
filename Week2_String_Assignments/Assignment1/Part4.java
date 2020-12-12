
/**
 * Write a description of part4 here.
 * 
 * @Zitao He
 * @version 1
 */

import edu.duke.*;



public class Part4 {
    
    public void printYoutubeLinks(){
        
        //URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");        
        
        String keyword = "youtube.com";
        int lengthOfKeyword = keyword.length();
        int indexLeftQuotation = 0;
        int indexRightQuotation = 0;
        int indexOfKeyword = 0;
        
        for (String word : ur.words()){

            if (word.toLowerCase().contains(keyword)){
                
                indexOfKeyword = word.toLowerCase().indexOf(keyword);
                //indexLeftQuotation = word.indexOf("\"");
                indexLeftQuotation = word.toLowerCase().lastIndexOf("\"", indexOfKeyword);
                indexRightQuotation = word.toLowerCase().indexOf("\"", indexOfKeyword);


                //System.out.println(indexRightQuotation +" and " +  indexLeftQuotation);
                //System.out.println(word);
                System.out.println(word.substring(indexLeftQuotation, indexRightQuotation + 1));
            }
        }
    }
}