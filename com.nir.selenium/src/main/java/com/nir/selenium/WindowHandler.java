package com.nir.selenium;

import org.openqa.selenium.WebDriver;

public abstract class WindowHandler {
	private final WebDriver driver;
	
	public WindowHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	protected abstract void openWindow(WebDriver driver);
	
	protected boolean isExpectedWindow(WebDriver driver, String originalWindowHandle) {
		return !driver.getWindowHandle().equals(originalWindowHandle);
	}
	
	public abstract void useWindow(WebDriver driver);
	
	public void run() {
		String originalWindowHandle = driver.getWindowHandle();
		
		openWindow(driver);
		try {
			for (String windowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);
				if (isExpectedWindow(driver, windowHandle)) {
					
					useWindow(driver);
					
					if (!driver.getWindowHandle().equals(originalWindowHandle))
						driver.close();
					
					return;
				}
			}
			throw new IllegalStateException("unable to find the correct window");	

		} finally {
			driver.switchTo().window(originalWindowHandle);
		}
	}
}