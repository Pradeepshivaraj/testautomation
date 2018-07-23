package org.test.automation.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.test.automation.base.BrowserManager;
import org.test.automation.exception.CFITRException;


public class PropertyReader extends BrowserManager {
	
	
	public static String getProperty(String key) throws FileNotFoundException, CFITRException {
		props = loadProperties();
		String prop = "";
		prop = props.getProperty(key);
		return prop;
	}

	public static Properties loadProperties() throws CFITRException, FileNotFoundException {

		String currentDir = "";
		String folder = "\\Properties\\";
		currentDir = CURRENTDIR + folder;
		Properties props = new Properties();
		FileReader reader = new FileReader(currentDir + "config.properties");
		try {
			props.load(reader);
		} catch (IOException e) {
		}
		return props;
	}

}
