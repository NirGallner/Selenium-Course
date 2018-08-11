package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoadingPageFactory {
	
	public static <T> T get(WebDriver driver, Class<T> pageObjectClass) {
			
		// Goto the URL, as specified in @Path
		driver.get(pageObjectClass.getAnnotation(Path.class).value());
				
		// Get the @Verify value annotation value from the class
		String xpath = pageObjectClass.getAnnotation(Verify.class).xpath();
		
		if (!xpath.equals(Verify.INVALID_XPATH))
			if (driver.findElements(By.xpath(xpath)).isEmpty())
				throw new IllegalStateException(String.format(	"expected xpath %s", xpath));
				
		// All is OK, continue loading the PageFactory
		return PageFactory.initElements(driver, pageObjectClass);
		
	}

}
