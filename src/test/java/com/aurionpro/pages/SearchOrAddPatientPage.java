package com.aurionpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchOrAddPatientPage {
	private By firstNameLocator = By.id("form_fname");
	private WebDriver driver;

	public SearchOrAddPatientPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFirstName(String firstName) {
		driver.findElement(firstNameLocator).sendKeys(firstName);
	}

}
