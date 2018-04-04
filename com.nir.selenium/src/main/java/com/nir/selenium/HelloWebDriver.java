package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HelloWebDriver {

	private WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterTest
	public void tear() {
	
		driver.quit();
	}
	
	
	@Test
	public void WikipediaTitle() throws InterruptedException {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		WebElement loginButton = driver.findElement(By.id("wp-submit"));
		
		Assert.assertEquals(loginButton.getAttribute("value"),"Log In" );
		Thread.sleep(2000);
	}
}
