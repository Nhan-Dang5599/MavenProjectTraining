package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	private String propertiesFilePath; // Properties file path
	private Properties properties;

	 // Constructor: Get properties file path when initializing
	 public PropertiesFile(String propertiesFilePath) {
		 this.propertiesFilePath = propertiesFilePath;
		 this.properties = new Properties();
	 }

	 // Read properties file
	 public void setPropertiesFile() {
		 try (FileInputStream fileIn = new FileInputStream(propertiesFilePath)) {
			 properties.load(fileIn); // Load file properties
		 } catch (IOException e) {
			 System.out.println("Cannot read properties file: " + e.getMessage());
			 e.printStackTrace();
		 }
	 }
	
	 // Get the value of a key in the properties file
	 public String getValuePropertiesFile(String keyPro) {
		 try {
			 return properties.getProperty(keyPro);
		 } catch (Exception e) {
			 System.out.println("Error getting value: " + e.getMessage());
			 e.printStackTrace();
			 return null;
		 }
	 }

	 // Write new values ​​to properties file
	 public void setPropValue(String keyProp, String value) {
		 try (FileOutputStream fileOut = new FileOutputStream(propertiesFilePath)) {
			 // Assign new value to key
			 properties.setProperty(keyProp, value);
			 // Save the properties file
			 properties.store(fileOut, "Update values ​​in properties file");
			 System.out.println("Successfully updated the value in the properties file.");
		 } catch (IOException e) {
			 System.out.println("Unable to write to properties file: " + e.getMessage());
			 e.printStackTrace();
		 }
	 }
}
