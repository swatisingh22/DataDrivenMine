package com.sampleProject.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestProperties {
	
	
public static void main	(String [] args ) throws Exception
{
	Properties config=  new Properties();
	
	Properties OR=  new Properties();
	
	
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\\\src\\\\test\\\\resources\\\\properties\\\\config.properties");
	config.load(fis);
	
    fis= new FileInputStream(System.getProperty("user.dir")+"\\\\src\\\\test\\\\resources\\\\properties\\\\/DataDrivenFramework/src/test/resources/properties/ObjectRepository.properties");
	config.load(fis);
	config.getProperty("browser");
	OR.getProperty("BankMangrLogin");
	
}

}