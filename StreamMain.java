import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StreamMain {

	public static void main (String args[]) {
		
		String[] dataToWrite = {
				"A A A A A A ",
				"B B B B B B B B", 
				"C C C C C C C C C C", 
				"D D D D D D D D D D D D"
		};
		
	
	
	
	try(FileSystem myZipFile = openZip(Paths.get("myFirstJavaZipFile.zip"))){ //get converts String name into a path name. Paths only work with the default file system. 
		copyZipFile(myZipFile);
		writeToZipFile(myZipFile,dataToWrite);
		writeUsingFilesMethod(myZipFile,dataToWrite);
		
	}catch (Exception e){
		System.out.println(e.getStackTrace());
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
