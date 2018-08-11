package com.nir.selenium;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Chapter6<W extends WebDriver & HasInputDevices> {
	
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
	
	//@Test
	public void noSuchElement() {
		LoginForm_FindBy login = LoadingPageFactory.get(driver, LoginForm_FindBy.class);
		login.username("admin").password("demo123").submit();
		
		try {
			login.username("admin");
		}catch (WebDriverException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void staleElement() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		WebElement login = driver.findElement(By.name("log"));
		login.sendKeys("admin");
		
		driver.findElement(By.name("pwd")).sendKeys("demo123");;
		driver.findElement(By.id("wp-submit")).click();
		
		login.sendKeys("hello");
		
	}
	
	@Test
	public void unexpectedTagNameException() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		WebElement login = driver.findElement(By.name("log"));
		new Select(login).selectByValue("123");;
		
		
	}
	
	@Test
	public void retryElementOnStale() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		WebElement login = driver.findElement(By.name("log"));
		login.sendKeys("admin");
		
		driver.findElement(By.name("pwd")).sendKeys("demo123");;
		driver.findElement(By.id("wp-submit")).click();
		
		try {
			login.sendKeys("hello");
		}
		catch (StaleElementReferenceException e) {
			
			if (driver.findElements(By.name("log")).isEmpty())
				throw e;
			else {
				login = driver.findElement(By.name("log"));
				login.sendKeys("hello");
			}
		}
	}
	
	@Test
	public void implicitWaitExample() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		LoginForm_FindBy login = LoadingPageFactory.get(driver, LoginForm_FindBy.class);
		login.username("admin").password("demo123").submit();
		
		//wait for 3 seconds, poll maximum 100 times
		WebDriverWait wait = new WebDriverWait(driver,3,100);
		
		
		// with message -the error message if we did not locate the element
		// we wait for the element to be visible
		WebElement posts = wait
							.withMessage("Could not find the element")
							.until(ExpectedConditions.visibilityOfElementLocated
									(By.cssSelector("#menu-posts .wp-menu-name"))
							);
		
		posts.click();
	}
	
	@Test
	public void fluentWaitExample() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		LoginForm_FindBy login = LoadingPageFactory.get(driver, LoginForm_FindBy.class);
		login.username("admin").password("demo123").submit();
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(3, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NotFoundException.class);
				
		
		// with message -the error message if we did not locate the element
		// we wait for the element to be visible
		WebElement posts = wait
							.withMessage("Could not find the element")
							.until(ExpectedConditions.visibilityOfElementLocated
									(By.cssSelector("#menu-posts .wp-menu-name"))
							);
		
		posts.click();
	}
	
	@Test
	public void localization() {
		//ResourceBundle strings = ResourceBundle.getBundle("strings", Locale.ENGLISH);
		ResourceBundle strings = ResourceBundle.getBundle("strings", Locale.forLanguageTag("he"));
		System.out.println(strings.getString("forgotten.password"));
	}

}
