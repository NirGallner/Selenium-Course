package com.nir.selenium;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


@ContextConfiguration(classes = WebDriverConfiguration.class)
@TestExecutionListeners(listeners= {ScreenshotTaker.class, DependencyInjectionTestExecutionListener.class})
public class InjectionsTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private WebDriver driver;
	
	@Autowired
	private URI baseUrl;
	
	@Test
	@DirtiesContext
	public void loginTest() {
		// Login
		driver.get("http://localhost/wordpress-4.9.2/wp-login.php");
		driver.findElement(By.name("log")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("demo123");
		driver.findElement(By.name("wp-submit")).click();
		
	}
	
	@Test
    public void loadIndexPage() throws Exception {
        driver.get(baseUrl + "/index.html");
    }
	

}
