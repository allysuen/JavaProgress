package dataFilter;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;

import org.junit.jupiter.api.Test;

//using TDD to build up the class FindRoles

public class TestingReadWriteFiles {
	
	private static final String FILE_LOCATION_XLSX = "resources/TestData.xlsx";
	private static final String FILE_LOCATION_CSV = "resources/TestData.csv";
	private static final String TARGET_LOCATION = "dataOutput.csv";
	
	//file should exist
	@Test 
	public void fileShouldExist() throws IOException {
		DataFilter.openFile(FILE_LOCATION_CSV);
		
	}
	
	//File should be opened successfully. 
	@Test 
	public void fileShouldBeRead() throws IOException{
		DataFilter.openAndReadFile(FILE_LOCATION_CSV);
	}
	
	//Using Apache POI library: 
	@Test 
	public void fileShouldBeReadUsingApache() throws IOException{
		DataFilter.openAndReadUsingApache(FILE_LOCATION_XLSX);
	}

	
	//Data was stored and can be accessed: 
	@Test
	public void dataShouldBeStored() throws IOException{
		DataFilter.openAndReadUsingApache(FILE_LOCATION_XLSX);
		//System.out.println(FindRoles.xlsxData.get(0).get(0));
		assertEquals("Title1", DataFilter.xlsxData.get(0).get(0));
	}
	
	
	//Write out data to a .csv file. 
	@Test 
	public void shouldWriteOutDataToACSVFile() throws IOException{
		DataFilter.writeDataToCSV(TARGET_LOCATION);
		
	}
	
	
	

}
