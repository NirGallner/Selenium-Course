package com.nir.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginForm {
	
	private final WebElement loginForm;
	
	public LoginForm(WebElement loginForm) {
		this.loginForm = loginForm;
	}
	
	public void submit() {		

		loginForm.findElement(By.name("wp-submit")).click();
	}
			
	public LoginForm username(String username) {		
			
		loginForm.findElement(By.name("log")).sendKeys(username);
		return this;
	}
		
	public LoginForm password(String password) {
			
		loginForm.findElement(By.name("pwd")).sendKeys(password);
		return this;
	}
	

}
