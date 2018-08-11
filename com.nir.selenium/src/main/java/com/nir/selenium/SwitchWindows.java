package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwitchWindows {
	
	private WebDriver driver;
	
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void tear() {
		//driver.quit();
	}
	
	//@Test
	public void switchToOnlyOtherWindow() {
		driver.get("https://www.w3schools.com/jsref/met_win_open.asp");
		
		String originalHanlde = driver.getWindowHandle();
		
		try{
			driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/a")).click();
			
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalHanlde)) {
					driver.switchTo().window(handle);
				}
			}
			
			try {
				//write some code here
			} finally {
				driver.close();
			}
		} finally {
			driver.switchTo().window(originalHanlde);
		}
	}
		
	@Test
	public void switchToNameWindow() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_a_target");
		driver.switchTo().frame("iframeResult");
		try {
			driver.findElement(By.xpath("/html/body/p/a")).click();
			driver.switchTo().window("_blank");
			
			try {
				Assert.assertEquals("W3Schools Online Web Tutorials", driver.getTitle(),"you are not on the page");
				//... do some work
			}
			finally {
				driver.close();
			}
			
		} finally {
			driver.switchTo().defaultContent();
		}	
	}
	
	@Test
	public void switchToInformationWindow() {
		
		driver.get("https://www.w3schools.com/jsref/met_win_open.asp");
		String originalHandle = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/a")).click();
		
		try {
			
			for (String windowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);
				
				if (driver.getTitle().equals("Tryit Editor v3.5"))
					break;
			}
			
			
			Assert.assertEquals("Tryit Editor v3.5", driver.getTitle(),"you are not on the page");
			//... do some work
		
			driver.close();
			
		} finally {
			driver.switchTo().window(originalHandle);
		}	
	}
	
	@Test
	public void encapsulatedWindowHandler() throws Exception{
		
		driver.get("https://www.w3schools.com/jsref/met_win_open.asp");
		
		new WindowHandler(driver) {
			
			@Override
			public void useWindow(WebDriver driver) {
				Assert.assertEquals("Tryit Editor v3.5", driver.getTitle(),"you are not on the page");
				//... do some work	
			}
			
			@Override
			protected void openWindow(WebDriver driver) {
				driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/a")).click();
			}
		}.run();;
	}
	
		
		
}

