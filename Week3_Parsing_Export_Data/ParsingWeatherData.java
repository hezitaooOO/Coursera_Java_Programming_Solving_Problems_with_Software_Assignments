
/**
 * Programming Exercise: Parsing Weather Data
 * Week 3 Assignment
 * @Zitao He
 * @version 1
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        
        CSVRecord coldestHourRow = null;
        int invalidTemp = -9999;
        
        for (CSVRecord record : parser){
            
            if (coldestHourRow == null){coldestHourRow = record;}            
            double coldestTemp = Double.parseDouble(coldestHourRow.get("TemperatureF"));           
            double currTemp = Double.parseDouble(record.get("TemperatureF"));              
            if (currTemp < coldestTemp && currTemp != invalidTemp){coldestHourRow = record;}
        
        }
        
        return coldestHourRow;
    } 
    
    public String fileWithColdestTemperature(){
        
        CSVRecord coldestRowInManyDays = null;
        CSVParser coldestParser = null;
        String coldestFileName = "";
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        
        int invalidTemp = -9999;
        
        for (File f : dr.selectedFiles()){
        
        FileResource fr = new FileResource(f);
        CSVParser currParser = fr.getCSVParser();
        CSVRecord coldestRowCurrDay = coldestHourInFile(currParser);
        
        if (coldestRowInManyDays == null){
            
            coldestRowInManyDays = coldestRowCurrDay;
            coldestParser = currParser;
            coldestFileName = f.getName();
            coldestFile = f;
        }
        
        double coldestTempOfManyDays = Double.parseDouble(coldestRowInManyDays.get("TemperatureF"));
        double coldestTempOfCurrDay = Double.parseDouble(coldestRowCurrDay.get("TemperatureF"));
        
        if (coldestTempOfCurrDay < coldestTempOfManyDays && coldestTempOfCurrDay != invalidTemp){
            coldestRowInManyDays = coldestRowCurrDay;
            coldestParser = currParser;
            coldestFileName = f.getName();
            coldestFile = f;
        }

        }
        
        System.out.println("Coldest day was in file " + coldestFileName);
        System.out.println("Coldest temperature on that day was " + coldestRowInManyDays.get("TemperatureF"));        
        System.out.println("All the Temperatures on the coldest day were:");

        for (CSVRecord record : new FileResource(coldestFile).getCSVParser()){
            System.out.println(coldestFileName + " " + record.get("DateUTC") + " " + record.get("TemperatureF"));
            //System.out.println(record.get("TimeEST"));

        }
        
        return coldestFileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        
        CSVRecord lowestHumidityRow = null;
        
        for (CSVRecord record : parser){
            
            if (lowestHumidityRow == null && record.get("Humidity").contains("N/A") == false){lowestHumidityRow = record;}
            
            if (record.get("Humidity").contains("N/A") == false){
                
                int lowestHumidity = Integer.parseInt(lowestHumidityRow.get("Humidity"));           
                int currHumidity = Integer.parseInt(record.get("Humidity"));
                if (currHumidity < lowestHumidity){lowestHumidityRow = record;}
                
            }
        
        }
        
        return lowestHumidityRow;
    }
    
    
    public String fileWithLowestHumidity(){
        
        CSVRecord lowestRowInManyDays = null;
        File lowestFile = null;
        DirectoryResource dr = new DirectoryResource();
        
        
        for (File f : dr.selectedFiles()){
        
        FileResource fr = new FileResource(f);
        CSVRecord lowestRowCurrDay = lowestHumidityInFile(fr.getCSVParser());
        
        if (lowestRowInManyDays == null){
            
            lowestRowInManyDays = lowestRowCurrDay;
            lowestFile = f;

        }
        
        if (lowestRowCurrDay.get("Humidity").contains("N/A") == false){
            
            int lowestTempOfManyDays = Integer.parseInt(lowestRowInManyDays.get("Humidity"));
            int lowestTempOfCurrDay = Integer.parseInt(lowestRowCurrDay.get("Humidity"));
            
            if (lowestTempOfCurrDay < lowestTempOfManyDays){
                lowestTempOfManyDays = lowestTempOfCurrDay;
                lowestFile = f;
            }
        
        }

        }
        
        return lowestFile.getName();
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        
        double totalValue = 0;
        double currTemp = 0;
        double invalidTemp = -999;
        int count = 0;
        
        for (CSVRecord record : parser){
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp != invalidTemp){
                totalValue = totalValue + currTemp;
                count ++;
            }
        }
        
        return (double)totalValue/count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        
        double totalValue = 0;
        double currTemp = 0;
        double invalidTemp = -999;
        int count = 0;
        
        for (CSVRecord record : parser){
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp != invalidTemp && record.get("Humidity") != "N/A" && Integer.parseInt(record.get("Humidity")) >= value){
                totalValue = totalValue + currTemp;
                count ++;
            }
        }
        
        if (count == 0){return (double)-1;}
        
        return (double)totalValue/count;
    }
    
    public void testColdestHourInFile(){
        
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("./data/nc_weather/2012/weather-2012-01-01.csv");        
        CSVParser parser = fr.getCSVParser(); 
        CSVRecord coldestHourRow = coldestHourInFile(parser);
        
        System.out.println("Coldest hour of the day: " + coldestHourRow.get("DateUTC") + ". " + "Coldest temperature: " + coldestHourRow.get("TemperatureF"));
    }
    
    public void testFileWithColdestTemperature(){
   
        fileWithColdestTemperature();    
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);    
        System.out.print("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));     
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avgHumidity = averageTemperatureWithHighHumidityInFile(parser, value);
        
        if(avgHumidity == -1){System.out.println("No temperatures with that humidity");}
        
        if(avgHumidity != -1) {System.out.print("Average Temp when high Humidity is " + avgHumidity);}
       
    }
    
    public void testFileWithLowestHumidity(){
        System.out.println("File with lowest humidity is: " + fileWithLowestHumidity());
    }
}
