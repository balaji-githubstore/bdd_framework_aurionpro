package com.aurionpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
	private By patientLocator=By.xpath("//div[text()='Patient']");

	private WebDriver driver;

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getMainPageTitle()
	{
		return driver.getTitle();
	}

	public void clickOnPatient()
	{
		driver.findElement(patientLocator).click();
	}
}
