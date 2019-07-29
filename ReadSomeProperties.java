import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadSomeProperties {

	public static void main(String[] args) {
		
		Properties defaultProp = new Properties();
		
		//Load default properties. 
		try(BufferedReader reader = Files.newBufferedReader(Paths.get("DefaultPersonProperties.properties"))){
			defaultProp.load(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Properties readProp = new Properties(defaultProp);
		
		//Read the random properties: 
		try(InputStream is = Files.newInputStream(Paths.get("RandomPersonProperties.properties"))){
			readProp.loadFromXML(is);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(
				"Name: " + readProp.getProperty("Name")+
				"\nAge: "+ readProp.getProperty("Age") + 
				"\nEmail: " + readProp.getProperty("Email") +
				"\nCity: " + readProp.getProperty("City") +
				"\nCan I access the default value? " + readProp.getProperty("Can I access the default value?")
				);

	}

}
