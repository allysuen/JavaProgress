package dataFilter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class TestingFilteringData {

	
	private static final String FILE_LOCATION_XLSX = "resources/TestData.xlsx";
	private static final String FILE_LOCATION_CSV = "resources/TestData.csv";
	private static final String TARGET_LOCATION = "dataOutput.csv";
	
	public static DataFilter myDataFilter;
	
	@BeforeClass
	public static void SetUp() throws IOException {
		
		
	}

	//Should be able to select a column
	@Test 
	public void shouldBeAbleToReturnSelectedColumn() throws IOException {
		myDataFilter.openAndReadUsingApache(FILE_LOCATION_XLSX);
		
		List <String> selectedCol = new ArrayList<>();
		selectedCol.add("Title3");
		selectedCol.add("Col3Value1");
		selectedCol.add("Col3Value2");
		selectedCol.add("Col3Value3");
		selectedCol.add("Col3Value4");
		selectedCol.add("Col3Value5");
		selectedCol.add("Col3Value6");
		selectedCol.add("Col3Value7");
		selectedCol.add("Col3Value8");
	
		
	
		List <String> result = new ArrayList<>(); 
		result = myDataFilter.selectColumn("Title3");
		//System.out.println(result);
		assertEquals(selectedCol,result);
		
	}
	

	//Should be able to filter out data.
	@Test 
	public void shouldBeAbleToFilterDataInSelectedColumn() throws IOException {
		 List <String> result = new ArrayList<>(); 
		 result = myDataFilter.filterColumn("Title2","Manchester");
		
		 for(String cell: result) {
			 assertEquals("Manchester",cell);
		 }
		
		}
	
	
	//Should be able to return all the indices of the matched fields
	@Test 
	public void shoulBeAbleToReturnFilteredIndices() throws IOException{
		List <Integer> result = new ArrayList<>(); 
		result = myDataFilter.filteredIndex("Title2", "Manchester");
		
		assertEquals("2",result.get(0).toString());
	
	
	}
	
	

	
}
		//Able to filter results based on the title. 
		
		//Maybe store them differently? As a class containing a var for each input field?

