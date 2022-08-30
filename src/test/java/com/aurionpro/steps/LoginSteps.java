package com.aurionpro.steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aurionpro.base.AutomationHooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
	
	
	@Given("I have browser with OpenEMR application")
	public void i_have_browser_with_open_emr_application() 
	{
		WebDriverManager.chromedriver().setup();
		
		AutomationHooks.driver=new ChromeDriver();
		AutomationHooks.driver.manage().window().maximize();
		AutomationHooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		AutomationHooks.driver.get("https://demo.openemr.io/b/openemr");
	}
	
	@When("I enter username as {string}")
	public void i_enter_username_as(String username) 
	{
		AutomationHooks.driver.findElement(By.id("authUser")).sendKeys(username);
	}
	
	@When("I enter password as {string}")
	public void i_enter_password_as(String password) 
	{
		AutomationHooks.driver.findElement(By.cssSelector("#clearPass")).sendKeys(password);
	}
	
	@When("I select language as {string}")
	public void i_select_language_as(String language) 
	{
		Select selectLan=new Select(AutomationHooks.driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
	}
	@When("I click on login")
	public void i_click_on_login() 
	{
		AutomationHooks.driver.findElement(By.id("login-button")).click();
	}
	
	@Then("I should get access to portal with title as {string}")
	public void i_should_get_access_to_portal_with_title_as(String expectedTitle) 
	{
		 Assert.assertEquals(AutomationHooks.driver.getTitle(), expectedTitle);
	}
	
	@Then("I should not get access to portal with error message as {string}")
	public void i_should_not_get_access_to_portal_with_error_message_as(String expectedError) {
		
		String actualError= AutomationHooks.driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText().trim();
		Assert.assertTrue(actualError.contains(expectedError));  //expect true
	}

}




