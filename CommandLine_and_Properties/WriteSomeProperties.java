import java.io.BufferedWriter;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class WriteSomeProperties {

	public static void main(String[] args) {
		
		Properties myProp = new Properties(); //new instance of property
		
		//set key value pair for property
		myProp.setProperty("Name", "Random Person"); 
		myProp.setProperty("Age", "100");
		myProp.setProperty("Email", "random_person@randommail.com");
		myProp.setProperty("City", "Random-ville");
		
		//store as an xml file
		try(OutputStream outStream = Files.newOutputStream(Paths.get("RandomPersonProperties.properties"))){
			myProp.storeToXML(outStream, "These are the properties for a random person.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		Properties defaultProp = new Properties(); //new instance of property
		
		//set key value pair for property
		defaultProp.setProperty("Name", "Default Person"); 
		defaultProp.setProperty("Age", "55");
		defaultProp.setProperty("Email", "default_person@randommail.com");
		defaultProp.setProperty("City", "Default-ville");
		defaultProp.setProperty("Can I access the default value?","yes");
		
		//store as a simple text file
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("DefaultPersonProperties.properties"))){
			defaultProp.store(writer, "These are the properties for a default person.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
