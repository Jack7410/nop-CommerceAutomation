package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObject.LoginPage;

public class LoginTest extends BaseClass {

	@Test
	void method() {
		System.out.println("Pass");
		LoginPage lPage = new LoginPage(driver);
		Assert.assertEquals(readProp.getLoginPageTitle(), driver.getTitle());

		lPage.setUserName("admin@yourstore.com");
		lPage.setUserPass("admin");
		lPage.clickLoginBtn();

		Assert.assertEquals(readProp.getHomePageTitle(), driver.getTitle());
	}

}
