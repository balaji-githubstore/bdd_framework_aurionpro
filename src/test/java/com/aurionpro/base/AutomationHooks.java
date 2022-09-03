package com.aurionpro.base;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
/**
 * 
 * @author Balaji
 *
 */
public class AutomationHooks {
	public WebDriver driver;
		
	
	@After
	public void endScenario()
	{
		if(driver != null)
		{
			driver.quit();
		}
	}

}
