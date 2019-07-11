package dataFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



//Java program that reads an excel file containing all the roles available, filters and output a file with the suitable roles. 

public class DataFilter {

	public static List <String> csvData = new ArrayList<>();
	public static List <List<String> > xlsxData = new ArrayList<>();
	
	//need to store each field as entry in array list
	
	
	
	//open file and it should be found
	public static void openFile(String fileLocation) throws IOException {
		try(FileInputStream myFile = new FileInputStream(fileLocation)){
	
		}catch(FileNotFoundException e) {
		System.out.println("File not found!");
		}
		
		
	}

	public static void openAndReadFile(String fileLocation) throws IOException {
			
			String data;
			try( BufferedReader reader = Files.newBufferedReader(Paths.get(fileLocation))){
				
				while((data = reader.readLine()) != null) {
					csvData.add(data);
					System.out.println(data);
				}
			}
			
			
			
		
		
	}

	public static void openAndReadUsingApache(String fileLocation) throws IOException {
		FileInputStream myFile = new FileInputStream(fileLocation);
		Workbook wb = WorkbookFactory.create(myFile);
		Sheet sheet = wb.getSheet("Sheet1");
		
		Iterator<Row> iterRow = sheet.rowIterator();
		//int rowCounter = 0; 
		//int fieldCounter = 0;
		
		while(iterRow.hasNext()) {
			//rowCounter =+ 1;
			Row thisRow = iterRow.next();
			List <String> oneEntry = new ArrayList<>();
			
			Iterator<Cell> iterCell = thisRow.cellIterator();
			
			while(iterCell.hasNext()) {
				Cell thisCell = iterCell.next();
				oneEntry.add(thisCell.getStringCellValue());
			}
			
			xlsxData.add(oneEntry);
			//System.out.println(oneEntry);
			
		}
		myFile.close();
		
	}

	public static void writeDataToCSV(String targetLocation) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream outputFileStream = new FileOutputStream(targetLocation);
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(targetLocation))){
		
		for(List<String> oneEntry : xlsxData) {
			for(String oneField: oneEntry) {
				writer.write(oneField);
				writer.write(',');
			}
			writer.newLine();
			
			}//outer for loop
		}
			outputFileStream.close();
	}
	

	public static List<String> selectColumn(String userInput) {
		//find correct index
		int index =0;
		int count =0;

		
		for(String name: xlsxData.get(0)) {
			//System.out.println(name + " and this is userInput: "+ userInput);
		 if(name.contentEquals(userInput)) {index = count; }
			count += 1;
		}
		
		
		//store values in arraylist 
		List<String> selectedCol = new ArrayList<>(); 
		for(List<String> entry: xlsxData) {
			selectedCol.add(entry.get(index));
		}
		
		
	//	System.out.println("selected: "+selectedCol);
		return selectedCol;
	}

	
		
	public static List<String> filterColumn(String userInput, String matcher) {
		List<String> filteredCol = selectColumn(userInput);
		
		Iterator <String> iter = filteredCol.iterator();
		
		while(iter.hasNext()) {
			String entry = iter.next();
		//	System.out.println(entry);
			if(entry.contentEquals(matcher)) {continue;}
			else {iter.remove(); }
			
		}
		
	
	//	System.out.println(filteredCol);
		return filteredCol; 
	
	}

	
	
	}//class
