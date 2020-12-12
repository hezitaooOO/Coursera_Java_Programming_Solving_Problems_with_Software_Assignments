
/**
 * Week 4 Assignment 1: Baby Names MiniProject: Total Births
 * 
 * @Zitao He
 * @version 1
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalBoyNames = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;        
        for (CSVRecord record : fr.getCSVParser(false)){
            int numberBorn = Integer.parseInt(record.get(2));
            totalBirths += numberBorn;            
            if (record.get(1).equals("M")){
                totalBoys += numberBorn;
                totalBoyNames ++;
            }
            else{
                totalGirls += numberBorn;
                totalGirlNames ++;
            }
        }
        System.out.println("Total number of boys: " + totalBoys);
        System.out.println("Total number of boy's names: " + totalBoyNames);
        System.out.println("Total number of girls: " + totalGirls);
        System.out.println("Total number of girl's names: " + totalGirlNames);        
        System.out.println("Total number of births: " + (totalBoys + totalGirls));     
    }
    
    public int getRank(int year, String name, String gender){

        String fileToProcess = "us_babynames/us_babynames_by_year/"+"yob"+Integer.toString(year)+".csv";
        FileResource fr = new FileResource(fileToProcess);        
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            
            if (record.get(1).equals(gender)){
                rank ++;
                if(record.get(0).equals(name)){
                    return rank;
                }
            }   
        }        
        return -1;
    }
    
    public String getName(int year, int rank, String gender){

        String fileToProcess = "us_babynames/us_babynames_by_year/"+"yob"+Integer.toString(year)+".csv";
        FileResource fr = new FileResource(fileToProcess);
        
        int currRow = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                currRow ++;
                if (currRow == rank){return record.get(0);}
            }                        
        } 
        return "NO NAME";
    }   
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName+ " if he or she was born in " + newYear + ".");
    }
    
    public double getAverageRank(String name, String gender){
        
        double avgRank = 0;
        int totalRankValue = 0;
        int numOfValidRanks = 0;
        DirectoryResource dr = new DirectoryResource();        
        
        for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        int currRank = 0;
            for (CSVRecord record : fr.getCSVParser(false)){
                if (record.get(1).equals(gender)){
                    currRank ++;
                    if(record.get(0).equals(name)){ //find a valid rank for the name in current year
                        totalRankValue += currRank;
                        numOfValidRanks ++;
                        break; //move to next file/year
                    }
                }  
            }
        }        
        if (numOfValidRanks == 0){return -1;};        
        return (double)totalRankValue/numOfValidRanks;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fileToProcess = "us_babynames/us_babynames_by_year/"+"yob"+Integer.toString(year)+".csv";
        FileResource fr = new FileResource(fileToProcess);        
        //int rank = 0;
        int totalBirths = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)){
            
            if (record.get(1).equals(gender)){
                //rank ++;

                if(record.get(0).equals(name)){
                    break;
                }
                
                totalBirths = totalBirths + Integer.parseInt(record.get(2));
            }   
        }
        
        return totalBirths;
    }
    
    public void testBabyBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank(){
        int highestRank = 0;
        int yearOfHighestRank = 0;
        //System.out.println(getRank(1971, "Frank", "M"));
        for(int year = 1880; year <= 2014; year++){
            if (highestRank == 0 && getRank(year, "Mich", "M") != -1){
                highestRank = getRank(year, "Mich", "M");
                yearOfHighestRank = year;
            }
            int currRank = getRank(year, "Mich", "M");
            if (currRank < highestRank && currRank != -1){
                highestRank = currRank;
                yearOfHighestRank = year;
            }
            System.out.println("The rank of year "+year+" is: "+currRank);
        }
        
        System.out.println("highest rank for this person in history occurred in year: " + yearOfHighestRank);
    }
    
    public void testGetName(){        
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
