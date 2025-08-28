package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObject.LoginPage;
import com.Utilities.ReadExcelData;

public class LoginTest extends BaseClass {

	@Test
	void method() {
		
		LoginPage lPage = new LoginPage(driver);
		
		try {
			Assert.assertEquals(readProp.getLoginPageTitle(), driver.getTitle());
			logger.info("Login Page tile matched");
		}catch(AssertionError e) {
			logger.error("Login page title mismatched getting following error :"+e);
		}
		
		
		takeScreenshot(driver, "method");
		lPage.setUserName("admin@yourstore.com");
		logger.info("User name entered");
		lPage.setUserPass("admin");
		logger.info("User password entered");
		lPage.clickLoginBtn();
		logger.info("Login btn clicked");

		try {
			Assert.assertEquals(readProp.getHomePageTitle(), driver.getTitle());
			logger.info("Loged in to application");
		}catch (AssertionError e) {
			logger.error("Login failed");
			
		}
		
		
		
	}

}
