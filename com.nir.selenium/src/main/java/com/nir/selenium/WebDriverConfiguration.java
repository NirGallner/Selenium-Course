
package com.nir.selenium;

import java.net.URI;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;

@Configuration
public class WebDriverConfiguration {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	@Description("Allows spcifying the desired browser specification, i.e browser type")
	public DesiredCapabilities desiredCapabilities(
			@Value("${webdriver.capabilities.browserName:firefox}") String browserName
	) { 
		return new DesiredCapabilities(browserName, "", Platform.ANY);
	}
	
	
	private WebDriver localWebDriver(DesiredCapabilities desiredCapabilities) {
		switch(desiredCapabilities.getBrowserName()) {
			case BrowserType.FIREFOX:
				System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\drivers\\geckodriver.exe");
				return new FirefoxDriver();
			case BrowserType.CHROME:
				return new ChromeDriver();
			case BrowserType.HTMLUNIT:
				return new HtmlUnitDriver();
			default:
				throw new IllegalStateException("Unknown Broser " + desiredCapabilities.getBrowserName());
		}
	}
	
	private WebDriver remoteWebDriver(URL remoteUrl, DesiredCapabilities desiredCapabilities) {
		return new Augmenter().augment(new RemoteWebDriver(remoteUrl, desiredCapabilities));
	}
	
	@Bean(destroyMethod="quit")
	public WebDriver webDriver(
			@Value("${webdriver.remote:false}") boolean isRemote,
			@Value("${webdriver.remote.url:http://localhost:4444/wd/hub}") URL remoteUrl,
			DesiredCapabilities desiredCapabilities) throws Exception 
	{
		return isRemote?
				remoteWebDriver(remoteUrl, desiredCapabilities) : localWebDriver(desiredCapabilities);
	}
	
	@Bean
	@Primary
	@Scope("prototype")
	public WebDriver cleanWebDriver(WebDriver driver) throws Exception {
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	@Bean
	public URI baseUrl(@Value("webdriver.baseurl:http://localhost:8080") URI value) {
		return value;
	}
	
	
}
