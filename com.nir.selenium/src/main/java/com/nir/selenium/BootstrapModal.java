package com.nir.selenium;

import org.apache.http.auth.Credentials;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class BootstrapModal implements Alert{
	private static final By INTPUT_SELECTOR = 
			By.cssSelector("input[type='text']");
	
	private final SearchContext searchContext;
	
	public BootstrapModal(SearchContext searchContext) {
		this.searchContext = searchContext;
	}
	
	@Override
	public void dismiss() {
		searchContext.findElement(By.cssSelector("button.btn.btn-default")).click();	
	}
	
	@Override
	public void accept() {
		searchContext.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}
	
	@Override
	public String getText() {
		return searchContext.findElement(INTPUT_SELECTOR).getAttribute("value");
	}
	
	@Override
	public void sendKeys(String keysToSend) {
		searchContext.findElement(INTPUT_SELECTOR).sendKeys(keysToSend);
	}
}
