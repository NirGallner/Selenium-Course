package com.nir.selenium;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class Modals {
	public static ExpectedCondition<Alert> modalIsDisplayed(){
		
		return new ExpectedCondition<Alert>() {
			
			@Override
			public Alert apply(WebDriver driver) {
				List<WebElement> bootStrapModal =driver.findElements(By.className("modal-dialog"));
				List<WebElement> otherModal = driver.findElements(By.className("other-modal"));
				return !bootStrapModal.isEmpty()
						? new BootstrapModal(bootStrapModal.get(0))
						: !otherModal.isEmpty()
						? new OtherModal(otherModal.get(0))
						: null;			
			}
		};	
	}

}
