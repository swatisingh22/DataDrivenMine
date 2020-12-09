package com.sampleProject.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sampleProject.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	
	@Test
	public void bankManagerLoginTest() throws InterruptedException
	{
		
		
		log.debug("\n Inside LoginTest");
		click("BankMangrLogin");
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustButton"))),"Login Not SUccessfull");
		
		log.debug("\\n LoginTest Successfully executed");
		
			
	}
}
