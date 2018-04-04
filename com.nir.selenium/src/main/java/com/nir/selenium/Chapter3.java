package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Chapter3<W extends WebDriver & HasInputDevices> {
//public class Chapter3<W extends WebDriver> {
	
private W driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		driver = (W) new ChromeDriver();
	}
	
	@AfterTest
	public void tear() {
	
		//driver.quit();
	}
	
	@Test
	public void SendKeysLogin() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		
		 WebElement username = driver.findElement(By.name("log"));
		 
		username.clear();
		username.sendKeys("admin");
		
		WebElement password = driver.findElement(By.name("pwd"));
		password.clear();
		password.sendKeys("demo123");
		
		driver.findElement(By.name("wp-submit")).click();
		
	}
	
	@Test
	public void keyboardLogin() {
		
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		
		driver
			.findElement(By.name("log"))
			.sendKeys("admin");
		
		new Actions(driver)
			.sendKeys(Keys.TAB)
			.perform();
		
		driver
			.switchTo().activeElement()
			.sendKeys("demo123");
		
		new Actions(driver)
			.sendKeys(Keys.ENTER)
			.perform();
			
	}
	
	@Test
	public void WYSIWYG_editor() {
		
		// Login to application
		keyboardLogin();
		
		// Not the best practice to get to the new post page, but for this example it will do
		driver.get("http://localhost/wordpress-4.9.2/wp-admin/post-new.php");
		
		// Find the frame and switch to it
		WebElement editorFrame = driver.findElement(By.id("content_ifr"));
		driver.switchTo().frame(editorFrame);
		
		// Find the editor and insert text
		WebElement textEditor = driver.findElement(By.id("tinymce"));
		textEditor.clear();
		textEditor.sendKeys("text to text editor. ");
		
		// Click the Italic button
		driver.switchTo().defaultContent();
		driver.findElement(By.id("mceu_2-button")).click();
		
		// Write additional text
		driver.switchTo().frame(editorFrame);
		textEditor.sendKeys("This text will appear Italic");
		
	}
	
	@Test
	public void mouseClicking() {
			
		//driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
			
		// Click on Remember Me
		//driver.findElement(By.name("rememberme")).click();
			
		SendKeysLogin();
		// Double Click
		WebElement rememberMe = driver.findElement(By.id("wp-admin-bar-my-account"));
		//driver.getMouse().doubleClick(((Locatable) rememberMe).getCoordinates());
		
		new Actions(driver)
			.doubleClick(rememberMe)
			.perform();
	}
	
	@Test
	public void contextMenuExample()
	{
		driver.get("https://talis.github.io/ng-context-menu/#/");
		WebElement context = driver.findElement(By.cssSelector("#panel-0"));
		

			
		new Actions(driver)
			.contextClick(context)
			.perform();
	}
	
	@Test
	public void selectExample()
	{
		// Login
		SendKeysLogin();
		
		// Navigate to the posts page
		driver.findElement(By.id("menu-posts")).click();
		
		// Locate the select deop-down
		Select categoryFilter = new Select(driver.findElement(By.id("cat")));
		
		// Choose a value
		categoryFilter.selectByVisibleText("Uncategorized");
		
		//... for a multi select - use one of the select methods again
		
	}

}
