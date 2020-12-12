
/**
 * Programming Exercise: Parsing Export Data
 * Week3 Assignment
 * 
 * @Zitao He 
 * @version 1
 */

import edu.duke.*;
import org.apache.commons.csv.*;


public class ParsingExportData {
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            //System.out.println(record.get("Country"));
            //System.out.println(record.get("Country") == country);
            if (record.get("Country").equals(country)){
                String output = record.get("Country") + ": " + record.get("Exports") +": "+ record.get("Value (dollars)");
                return output;
            }
        }
        
        return " NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
            
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            
            if (record.get("Exports").contains(exportItem)){
                count ++;
            }
            
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            if (record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void test(){
    
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    //System.out.print(countryInfo(parser, "Nauru"));
    //listExportersTwoProducts(parser, "cotton", "flowers");
    //System.out.println(numberOfExporters(parser, "cocoa"));
    bigExporters(parser, "$999,999,999,999");
    }
    
}

