package dataFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



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

	
		
	public static List<String> filterColumn(String criteria, String matcher) {
		List<String> filteredCol = selectColumn(criteria);
		
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

	public static List<Integer> filteredIndex(String criteria, String matcher) {
		List<Integer> indexArray = new ArrayList<>();
		int count=0;
		
		List<String> filteredCol = selectColumn(criteria);
	
		Iterator <String> iter = filteredCol.iterator();
		
		while(iter.hasNext()) {
			String entry = iter.next();
		// System.out.println(entry);
			if(entry.contentEquals(matcher)) {indexArray.add(count); count +=1; }
			else {count +=1; continue;}
			
		}
		//System.out.println(filteredIndex);
	
		return indexArray;
	}

	public static List <List<String> > filteredData(String criteria, String matcher) throws IOException {
		List <Integer> indexArray = new ArrayList<>(); 
		indexArray = filteredIndex(criteria,matcher);
		List <List <String>> filteredArray = new ArrayList<>(); 
		
		for(int index: indexArray) {
			filteredArray.add(xlsxData.get(index));
		}
		
		
		
		
		
		return filteredArray;
		
		
	}

	public static void writeToExcelFile(List<List<String>> result, String fileLocation) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Filtered Results");
		int rowCount =0;
		//column headings: 
		Row titleRow = sheet.createRow(rowCount++);
		int titleCellCount = 0; 
		for(String title: xlsxData.get(0)) {
			Cell cell = titleRow.createCell(titleCellCount++);
			cell.setCellValue(title);
		}
		
	
		
		for(List<String> entry: result) {
			Row row = sheet.createRow(rowCount++);
			int cellCount =0; 
			
			for(String field: entry) {
				Cell cell = row.createCell(cellCount++);
				cell.setCellValue(field);
			}
		}
		
		
		try {
			FileOutputStream os = new FileOutputStream(new File(fileLocation) );
			wb.write(os);
			os.close();
		}catch(Exception e) { e.printStackTrace();}
		
		
	}

	
	
	}//class
