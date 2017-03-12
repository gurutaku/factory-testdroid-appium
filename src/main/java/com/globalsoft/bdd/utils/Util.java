package com.globalsoft.bdd.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

	public static final String getXpath(String pageName, String elementLocator) throws IOException {
		Properties property = new Properties();
		String pagePropertyFile = pageName + ".properties";
		InputStream inStream = Util.class.getClassLoader().getResourceAsStream(pagePropertyFile);
		if (inStream != null) {
			property.load(inStream);
		} else {
			throw new FileNotFoundException(pagePropertyFile + "' not found in the classpath");
			
		}
		String xpath = property.getProperty(elementLocator);
		return xpath;
	}
}
