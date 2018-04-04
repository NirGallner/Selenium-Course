package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Chapter4<W extends WebDriver & HasInputDevices> {
	
	private W driver;
	
	@BeforeTest
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		driver = (W) new FirefoxDriver();
	}
	
	@AfterTest
	public void tear() {
	
		//driver.quit();
	}
	
	@Test
	public void checkIfElementPresent() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		Assert.assertFalse(driver.findElements(By.id("invisible")).isEmpty());
		
	}
	
	@Test
	public void checkIfElementVisible() {
		
		// Login
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		driver.findElement(By.name("log")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("demo123");
		driver.findElement(By.name("wp-submit")).click();
		
		
		Assert.assertTrue(driver.findElement(By.id("addbtn")).isDisplayed());
		
	}
	
	
	public void login() {
		// Login
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		driver.findElement(By.name("log")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("demo123");
		driver.findElement(By.name("wp-submit")).click();
	}
	
	@Test
	public void AssertTitle() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		Assert.assertTrue(driver.getTitle().contains("WordPress"), "The site is not WordPress site");
	}
	
	@Test
	public void readText() {
		
		login();
		
		// Assert on the h1 tag
		Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Dashboard"));
	}
	
	@Test
	public void findTextOnPage() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		// Make sure the original page does not contain this error message 
		Assert.assertFalse(driver.getPageSource().contains("is incorrect")); 
		
		driver.findElement(By.name("log")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("BadPass");
		driver.findElement(By.name("wp-submit")).click();
		
		// Make sure the original page does not contain this error message 
		Assert.assertTrue(driver.getPageSource().contains("is incorrect"));
		
	}
	
	@Test
	public void findElementByText() {
		
		login();
		
		// Make sure the element is on the Dashboard page
		Assert.assertNotNull(driver.findElement(By.xpath("//*[text()='WordPress 4.9.2 Demo']")));
		
		// Click the link
		driver.findElement(By.xpath("//*[text()='WordPress 4.9.2 Demo']")).click();
	}
	
	@Test
	public void getElementAttribute() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		Assert.assertTrue(driver.findElement(By.name("wp-submit")).getAttribute("value").equals("Log In"));
	}
	
	@Test
	public void prefilledForm() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		driver.findElement(By.name("log")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("demo123");
		
		// Username text box
		String username    = driver.findElement(By.name("log")).getAttribute("value");
		
		// Password text box
		String password    = driver.findElement(By.name("pwd")).getAttribute("value");
		
		// Submit button
		String submitBtn   = driver.findElement(By.name("wp-submit")).getAttribute("value");
		
		// Remember me check box
		boolean rememberMe = driver.findElement(By.name("rememberme")).isSelected();
		
		// Submit login form 
		driver.findElement(By.name("wp-submit")).click();
		
		//Text Area
		String QuickDraft = driver.findElement(By.id("content")).getText();
		
		// Navigate to the posts page
		driver.findElement(By.id("menu-posts")).click();

		// single Drop-down select
		Select dates  = new Select(driver.findElement(By.id("filter-by-date")));
		String datesValue = dates.getFirstSelectedOption().getText();
	}
	
	@Test
	public void examineStyle() {
		
		login();
		
		WebElement textArea = driver.findElement(By.id("content"));
		
		System.out.println(textArea.getCssValue("height"));
		System.out.println(textArea.getCssValue("overflow-y"));
		System.out.println(textArea.getCssValue("color"));
		
		
	}
	
	

}
