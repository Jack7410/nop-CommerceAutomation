package com.TestCases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.Utilities.ReadProperties;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Eager;

public class BaseClass {
	
	
	
	WebDriver driver; 
	ReadProperties readProp;
	@Parameters("Browser")
	@BeforeTest
	
	// Method to set the browser
	// We can perform testing 1) Chrome 2)Firefox 3) Edge browser
	void setBrowser(String browser) throws Throwable {
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt =new ChromeOptions();
			opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver=new ChromeDriver(opt); 
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt=new FirefoxOptions(); 
			opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver=new FirefoxDriver(opt);
		}else {
			WebDriverManager.edgedriver().setup();
			/*
			 * EdgeOptions opt=new EdgeOptions(); opt.setPageLoadStrategy("EAGER");
			 */
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		readProp=new ReadProperties(); 
		driver.get(readProp.getUrl());
		
	}
	
	
	// Method for close the browser
	@AfterTest
	void tearDown() {
		driver.close();
	}

}
