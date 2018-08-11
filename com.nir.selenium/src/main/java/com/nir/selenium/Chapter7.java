package com.nir.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Chapter7<W extends WebDriver & HasInputDevices> {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setup() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		driver = (W) new FirefoxDriver();
		Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
	}
	
	
	@Test
	public void someTest() {
		//... do some testing
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
