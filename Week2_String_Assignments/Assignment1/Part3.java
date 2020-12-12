
/**
 * Write a description of Part3 here.
 * 
 * @Zitao 
 * @version 1
 */
public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb){
        int occurrence = 0;
        int firstOccurIndex = 0;
        int aLength = stringa.length();
        if (stringb.indexOf(stringa)!= -1){
            
            firstOccurIndex = stringb.indexOf(stringa);
            occurrence++;
        }
        
        if (stringb.indexOf(stringa, firstOccurIndex + aLength) != -1){
            occurrence++;
        }
        
        return (occurrence == 2) ? true : false;
    }
    
    public String lastPart(String stringa, String stringb){
        return (stringb.indexOf(stringa)!= -1) ? stringb.substring(stringb.indexOf(stringa) + stringa.length()) : stringb;
    }
    
    public void test(){
        String stringa = "an";
        String stringb = "banana";
        
        String stringc = "zoo";
        String stringd = "forest";
        
        System.out.println(lastPart(stringa, stringb));
        System.out.println(lastPart(stringc, stringd));
    }
}
