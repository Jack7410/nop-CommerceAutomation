package com.TestCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.PageObject.LoginPage;
import com.Utilities.ReadProperties;


import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Eager;

public class BaseClass {
	
	
	
	WebDriver driver; 
	ReadProperties readProp;
	Logger logger; 
	
	@Parameters("Browser")
	@BeforeTest
	
	// Method to set the browser
	// We can perform testing 1) Chrome 2)Firefox 3) Edge browser
	void setBrowser(String browser) throws Throwable {
		
		logger= LogManager.getLogger(this.getClass()); 
		
		
		try {
			if(browser.equalsIgnoreCase("Chrome")) {
				
				logger.info("*** Browser selected as "+browser);
				WebDriverManager.chromedriver().setup();
				ChromeOptions opt =new ChromeOptions();
				opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
				driver=new ChromeDriver(opt); 
			}else if(browser.equalsIgnoreCase("firefox")) {
				logger.info("*** Browser selected as "+browser);
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions opt=new FirefoxOptions(); 
				opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
				driver=new FirefoxDriver(opt);
			}else {
				logger.info("*** Browser selected as "+browser);
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
			logger.info("Url entered"+readProp.getUrl());
		}catch(WebDriverException e){
			logger.info("Unable to launch the brower");
			logger.error(e.getMessage());
			
			
		}

		
	}
	
	
	// Method for close the browser
	@AfterTest
	void tearDown() {
		
		driver.close();
		logger.info("Browser closed");
		logger.info("****************************************************");
	}
	
	public void takeScreenshot(WebDriver driver,String name) {
		File sS=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File path=new File(".//Screenshots/"+name+".png");
		
		try {
			FileUtils.copyFile(sS, path);
		} catch (IOException e) {
			System.out.print("Screenshot not found: ");
			e.printStackTrace();
		} 
		
	}

}
