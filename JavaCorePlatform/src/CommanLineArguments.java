import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommanLineArguments {

	private static ArrayList<String> storedLines;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "LetterContents.txt";
		String output = "MyLetter.txt";
		
		readFile(args[0]);
		writeFile(args[1],args[2],args[3]);
		
	}//main

	
	//method to read in file:
	public static void readFile(String inputFileName) {
		
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName))){
			String line; 
			storedLines = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				storedLines.add(line);
			}
		}catch(Exception e) {
			System.out.println("Exception caught!");
		}
	}
	
	
	//method to write out file as letter: 
	public static void writeFile(String outputFileName, String yourName, String myName) {
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName))){
			writer.write("Dear "+ yourName + ", \n\n"); 
			
			for(String line: storedLines) {
				writer.write(line);
				writer.newLine();
			}
			writer.write("\n \nYours sincerely, \n" + myName);
			
		}catch(Exception e) {
			System.out.println("Exception caught!");
		}
	}
	

	
	
	
}
