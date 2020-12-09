package com.sampleProject.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sampleProject.base.TestBase;
import com.sampleProject.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")

	public void addCustomerTest(Hashtable<String,String>data) throws InterruptedException
	{
		
		if (!data.get("runmode").equals("Y"))
		{
			throw new SkipException("Skipping the test data as runmode is No");
		}
		click("addCustButton");
		type("firstName",data.get("firstName"));
		type("lastName",data.get("lastName"));
		type("postCode",data.get("postCode"));		
		click("addbtn");	
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		
		alert.accept();
		
		Thread.sleep(3000);
		
		
	}
	
	
}
