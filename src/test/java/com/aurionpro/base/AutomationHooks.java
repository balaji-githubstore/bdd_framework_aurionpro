package com.aurionpro.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
/**
 * 
 * @author Balaji
 *
 */
public class AutomationHooks {
	public static WebDriver driver;
		
	
	
	@After
	public void endScenario()
	{
				
		if(AutomationHooks.driver != null)
		{
			AutomationHooks.driver.quit();
		}
	}

}
