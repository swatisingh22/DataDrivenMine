package com.sampleProject.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sampleProject.base.TestBase;
import com.sampleProject.utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")

	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException
	{
		
		click("openAccount_css");
		selectText("customer_css",data.get("customer"));
		selectText("currency_css",data.get("currency"));
		click("process_css");
		Thread.sleep(2000);
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		
		
		alert.accept();
		
		
		
	}
}
