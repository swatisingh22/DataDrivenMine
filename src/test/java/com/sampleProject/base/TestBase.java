package com.sampleProject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sampleProject.utilities.ExcelReader;
import com.sampleProject.utilities.ExtentManager;

public class TestBase {
	
	
 /* All initialization in base class 
	-Webdriver
	-Properties
	-Logs
	-ExtentReports 
	-DB
	-Excel Reading 
	-Mailing part 	
	*/
public static WebDriver driver;
	
public static Properties config=  new Properties();
	
public static Properties OR=  new Properties();

public static FileInputStream fis;

public static Logger log=Logger.getLogger("devpinoyLogger");

public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excelDatasheet\\TestData.xlsx");

public static WebDriverWait wait;

public ExtentReports rep=ExtentManager.getInstance();

public static ExtentTest test;

@BeforeSuite	
	public void setUp() throws Exception
	{
	
		if (driver==null)
			
			
		{
			 fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
				config.load(fis);
			
				log.debug("Config file loaded ");
			 fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
				OR.load(fis);
				
				log.debug("\n  OR File loaded ");
				
				
		}
	
		
		if (config.getProperty("browser").equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
				
			log.debug("\n Chrome Launched ");
		}
		
		driver.get(config.getProperty("testsiteurl"));
		
		log.debug(("\n Navigated to  ")+ config.getProperty("testsiteurl"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
		wait=new WebDriverWait(driver, 5);
		
	}


public void click(String locator)
{
	driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
	test.log(LogStatus.INFO, "clicking on :"+locator );
}

public void type(String locator, String value )
{
	driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
	test.log(LogStatus.INFO, "Typing in  :"+locator +"entered value as : "+value );
}





   public boolean isElementPresent(By by)
   {
	
	 try {
		   
		   driver.findElement(by);
		   return true;
	   }catch(NoSuchElementException e){
		   return false;
	   }   
	   
   }
   
   
   static WebElement dropDown;

   public void selectText(String locator,String value)
   {
   	dropDown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
   	
   	Select select=new Select(dropDown);
   	select.selectByVisibleText(value);
   	
   	test.log(LogStatus.INFO, "Selecting from DropDown : "+locator  +"Value as " +value);
   }
@AfterSuite
	public void tearDown()
	{
		if (driver!=null)
		{
			driver.quit();
		}
		
		log.debug("\n Test Execution COmpleted ");
	}
	
		
	
}
