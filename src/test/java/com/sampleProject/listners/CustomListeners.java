package com.sampleProject.listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.sampleProject.base.TestBase;
import com.sampleProject.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {
	
	

	public void onTestStart(ITestResult result) {
		test=rep.startTest(result.getName().toUpperCase());
		
		//runmode
		if (!(TestUtil.isTestRunnable(result.getName(), excel)))
		{
			throw new SkipException("Skipping the test"+result.getName()+" as RunMode is N");
		}
	}

	public void onTestSuccess(ITestResult result) {
	
		TestBase.test.log(LogStatus.PASS, result.getName().toUpperCase()+" Pass");
		rep.endTest(test);
		
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		Reporter.log("Capturing Screenshots");
		Reporter.log("<a target=\"_blank\"href="+TestUtil.screenshotName+">Screenshots</a>");
		
		TestBase.test.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception :"+result.getThrowable());
		
		TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		
		rep.flush();
		
		
	}

	public void onTestSkipped(ITestResult result) {
	
		test.log(LogStatus.SKIP, result.getName()+" Skipping the Test As run mode is No ");
	     rep.endTest(test);
		
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
