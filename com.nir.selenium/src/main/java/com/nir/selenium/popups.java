package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Test
public class popups<W extends WebDriver & HasInputDevices> {
	
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
	public void alertDismiss() {
		
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(1) > button")).click();
		
		driver.switchTo().alert().dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), 
				"You successfuly clicked an alert",
				"You did not dismiss the pop-up");
	}
	@Test
	public void alertEnterText() {
		
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(3) > button")).click();
		String msg = "This is so cool";
		
		driver.switchTo().alert().sendKeys(msg);
		driver.switchTo().alert().accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), 
				"You entered: " + msg,
				"You did not dismiss the pop-up");
	}
	
	@Test
	public void demonstrateModal() {
		driver.get("https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_modal_sm&stacked=h");
		driver.switchTo().frame("iframeResult");
		
		driver.findElement(By.cssSelector("body > div > button")).click();
		new WebDriverWait(driver, 2).until(Modals.modalIsDisplayed());
		
		BootstrapModal bootstrap = new BootstrapModal(driver);
		bootstrap.dismiss();
	}

}
