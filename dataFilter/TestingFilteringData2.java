package dataFilter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestingFilteringData2 {

	
	private static final String FILE_LOCATION_XLSX = "resources/TestData.xlsx";
	private static final String TARGET_LOCATION = "filteredOutput.xlsx";
	
	public static DataFilter myDataFilter;
	
	//Given the indices, should be able to return the whole rows of data values. 
	@Test 
	public void shouldBeAbleToReturnFilteredResults() throws IOException{
		myDataFilter.openAndReadUsingApache(FILE_LOCATION_XLSX);
		List<List<String>> result = new ArrayList<>();
		result = myDataFilter.filteredData("Title2", "Manchester");
		
		assertEquals("Col1Value2", result.get(0).get(0));
		
	}
	
	@Test 
	public void shouldBeAbleToWriteToExcelFile() throws IOException{
		//DataFilter thisDataFilter = null; 
		//thisDataFilter.openAndReadUsingApache(FILE_LOCATION_XLSX);
		List<List<String>> result = new ArrayList<>();
		result = myDataFilter.filteredData("Title2", "Manchester");
		
		DataFilter.writeToExcelFile(result, TARGET_LOCATION); 
		
		
	}
	

	
}
