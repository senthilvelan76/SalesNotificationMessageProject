package test.jp.salesmessage.main;
import java.io.BufferedReader;
import java.io.FileReader;

import test.jp.salesmessage.impl.SalesMessageDetails;

/**

 * This class is used for to run the application.

 * Requires input file that containing message details of sales.
 * Processed sales and generate report based on input
 *
 */


public class SalesNotificationMessageMainClass {

    public static void main(String[] args) {
        
    	SalesNotificationMessageMainClass sn = new SalesNotificationMessageMainClass();
    	String fileName= "inputfile/salesmessagedetails.txt";
    	sn.processSalesMessage(fileName);
    	
    }
    
    
    //Process the sales message notification
    public String processSalesMessage(String fileName){
    	
    	SalesMessageDetails salesMessageDetails = new SalesMessageDetails();
        
            String salesMessageLine;
            //Read input file from context path
            
            try(BufferedReader inputFile = new BufferedReader(new FileReader(fileName));) {
            while((salesMessageLine = inputFile.readLine()) != null) {
                // Read sales messages line by line and processed with parse the messages type
            	salesMessageDetails.salesMessageProcess(salesMessageLine);  
            	
                // Processing every 10th iteration of messages and It will be stoped on 50th iteration of messages              
            	salesMessageDetails.salesMessageLog.report();
            }
            } catch (Exception e) {
            	System.out.println("Error in Processing of sales message notification");
            	return "ProcessFailure";
               
            }
        
    	return "ProcessCompleted";
    }

    public String fileNameValidate(String fileName){
    	if(fileName.equalsIgnoreCase("inputfile/salesmessagedetails.txt")){
    		
    		return "FileValid";
    	}else{
    		return "FileInValid";
    	}
    }
}