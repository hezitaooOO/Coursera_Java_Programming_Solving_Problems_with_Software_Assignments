/**
 * Coursera Course: Java Programming: Solving Problems with Software. 
 * Week 1 
 * Assignment: Calculate perimeter.
 * 
 * @Zitao He
 * @version 1
 */

import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    
    public int getNumPoints (Shape s){

        int pointsNumber = 0;
        
        for (Point point : s.getPoints()){
            pointsNumber ++;
        }
        return pointsNumber;
    }
    
    public double getAverageLength(Shape s){
        return (double) getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s){
        
        double largestSide = 0;
        
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            
            double currDist = prevPt.distance(currPt);

            if (currDist > largestSide)
                largestSide = currDist;
            prevPt = currPt;
        }
        
        return largestSide;
    }
    
    public double getLargestX(Shape s){
        
        double largestX = 0;
       
        for (Point point : s.getPoints()) {
            // Find distance from prevPt point to currPt 

            if (point.getX() > largestX)
                largestX = point.getX();
            
        }
        return largestX;
    }
    
    public double getLargestPerimeterMultipleFiles(){
        
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        
        for (File f : dr.selectedFiles()){
             FileResource fr = new FileResource(f);
             Shape s = new Shape(fr);
             double perimeter = getPerimeter(s);
             if (perimeter > largestPerimeter)
                largestPerimeter = perimeter;
             }
        
        return largestPerimeter;

    }

    public String getFileWithLargestPerimeter(){
        
        DirectoryResource dr = new DirectoryResource();
        File fileHasLargestPerimeter = null;
        double largestPerimeter = 0.0;
        
        for (File f : dr.selectedFiles()){
             FileResource fr = new FileResource(f);
             Shape s = new Shape(fr);
             double perimeter = getPerimeter(s);
             if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
                fileHasLargestPerimeter = f;
             }
        }
        
        return fileHasLargestPerimeter.getName();
    }
    
    public void testPerimeter () {
      
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numOfPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("number of points = " + numOfPoints);
        System.out.println("perimeter = " + length);
        System.out.println("average length = " + averageLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles(){
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        String fileHasLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("largest perimeter in multiple files = " + largestPerimeter);
        System.out.println("file name that has the largest perimeter = " + fileHasLargestPerimeter);
    
    }
    
    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeterMultipleFiles();

    }
}
