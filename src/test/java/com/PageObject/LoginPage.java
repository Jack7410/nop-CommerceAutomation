package com.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	public LoginPage(WebDriver driver) {
		ldriver=driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "input[type='email']")
	WebElement userName; 
	
	public void setUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	@FindBy(xpath = "//*[starts-with(@class,'pass') ][@id='Password']")
	WebElement userPass;
	
	public void setUserPass(String userPass) {
		this.userPass.clear();
		this.userPass.sendKeys(userPass);
	}
	
	@FindBy(xpath = "//*[text()='Log in']")
	WebElement loginBtn;
	
	public void clickLoginBtn() {
		loginBtn.click();
	}

}
