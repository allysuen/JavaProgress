import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
//import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamMain {

	public static void main (String args[]) {
		
		String[] dataToWrite = {
				"A A A A A A ",
				"B B B B B B B B", 
				"C C C C C C C C C C", 
				"D D D D D D D D D D D D"
		};
		
		int number = 500;
		String animal = "whales";
		String verb ="running around";
		int amount = 2000000; 
		String targetAnimal = "rabbits"
	;
	
	try(FileSystem myZipFile = openZip(Paths.get("myFirstJavaZipFile.zip"))){ //get converts String name into a path name. Paths only work with the default file system. 
		copyZipFile(myZipFile);
		writeToZipFile(myZipFile,dataToWrite);
		writeUsingFilesMethod(myZipFile,dataToWrite);
		writeFormattedFiles(myZipFile,number,animal, verb,  amount, targetAnimal);
		readFileAndPrintItOut(myZipFile);
		
	}catch (Exception e){
		System.out.println(e.getStackTrace());
	}
	}
	
	private static void readFileAndPrintItOut(FileSystem myZipFile) throws IOException {
		List <String> readData = new ArrayList<>();
		String data;
		try( BufferedReader reader = Files.newBufferedReader(Paths.get("MyTextFile.txt"))){
			
			while((data = reader.readLine()) != null) {
				readData.add(data);
				System.out.println(data);
			}
		}
		
		System.out.println(readData);
	}
	

	private static void writeFormattedFiles(FileSystem myZipFile, int number, String animal, String verb, int amount, String targetAnimal) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(myZipFile.getPath("FormattedTextFile.txt")); 
		try(Formatter f = new Formatter(writer)){ //Formatter wraps around buffered writer and in try with resources, so it will automatically close the buffered writer as well since it is closeable.
			f.format("Once upon a time, there were %d %s %s %,d %s.", number, animal, verb, amount, targetAnimal);
		}
	}

	private static void writeUsingFilesMethod(FileSystem myZipFile, String[] dataToWrite) throws IOException {
		Files.write(myZipFile.getPath("MyWrittenFile2.txt"), Arrays.asList(dataToWrite),
				Charset.defaultCharset(),StandardOpenOption.CREATE);
		
	}

	private static void writeToZipFile(FileSystem myZipFile, String[] dataToWrite) throws IOException {
		try(BufferedWriter writer = Files.newBufferedWriter(myZipFile.getPath("MyWrittenFile1.txt"))){
			for(String line : dataToWrite) {
				writer.write(line);
				writer.newLine();
			}
			
		}
		
	}

	private static void copyZipFile(FileSystem myZipFile) throws IOException {
		Path sourceFile = Paths.get("MyTextFile.txt"); //path for source file
		Path destFile = myZipFile.getPath("/CopiedTextFile.txt"); //path for destination file and its new name
		Files.copy(sourceFile,destFile,StandardCopyOption.REPLACE_EXISTING); //copy command also telling it to replace if already existing
	}

	private static FileSystem openZip(Path zipPath) throws URISyntaxException, IOException {
		//Properties: 
		Map<String,String> properties = new HashMap<>();
		properties.put("create", "true"); 
		
		URI zipURI = new URI("jar:file", zipPath.toUri().getPath(),null); //Unified Resource Identifier, zip file has scheme jar:file
		FileSystem zipFileSys = FileSystems.newFileSystem(zipURI,properties); //create new zip file with URI and properties.
	
		return zipFileSys;
	}
	
	
}
