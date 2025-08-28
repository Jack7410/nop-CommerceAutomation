package com.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadProperties {
	
	FileInputStream input; 
	Properties prop;
	
	public ReadProperties() throws Throwable {
		input=new FileInputStream(".//TestData/data.properties");
		prop=new Properties();
		prop.load(input);
	}
	
	public String getUrl() {
		return prop.getProperty("Url");
	}
	
	public String getLoginPageTitle() {
		return prop.getProperty("LoginPageTitle");
	}
	
	public String getHomePageTitle() {
		return prop.getProperty("HomePageTitle");
	}
}
