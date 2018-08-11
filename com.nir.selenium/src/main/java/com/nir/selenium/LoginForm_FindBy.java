
package com.nir.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("http://localhost/wordpress-4.9.2/wp-login.php")
@Verify(xpath="//*[@id=\"loginform\"]")
public class LoginForm_FindBy {
	private WebElement log;
	private WebElement user_pass;
	
	
	@FindBy(name = "wp-submit")
	private WebElement btnLogin;
	
	@FindBy(id = "rememberme")
	private WebElement chkRememberMe;
	
	
	public void submit() {		
		btnLogin.click();
	}
			
	public LoginForm_FindBy username(String username) {
		log.sendKeys(username);
		return this;
	}
		
	public LoginForm_FindBy password(String password) {
		user_pass.sendKeys(password);
		return this;
	}
	
	
}
