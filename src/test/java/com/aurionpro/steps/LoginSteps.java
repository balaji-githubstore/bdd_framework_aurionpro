package com.aurionpro.steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aurionpro.base.AutomationHooks;
import com.aurionpro.base.DataTransfer;
import com.aurionpro.pages.LoginPage;
import com.aurionpro.pages.MainPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
	
	private AutomationHooks hooks;
	private DataTransfer dt;
	private LoginPage loginPage;
	private MainPage mainPage;
	
	public LoginSteps(AutomationHooks hooks,DataTransfer dt)
	{
		this.hooks=hooks;
		this.dt=dt;
		System.out.println(hooks.driver);
//		System.out.println(dt.width);
//		System.out.println(dt.height);
//		
//		dt.width=5000;
	}
	
	
	@Given("I have browser with OpenEMR application")
	public void i_have_browser_with_open_emr_application() 
	{
		WebDriverManager.chromedriver().setup();
	
		hooks.driver=new ChromeDriver();
		hooks.driver.manage().window().maximize();
		hooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		hooks.driver.get("https://demo.openemr.io/b/openemr");
		
		initPageObjects();
	}
	
	public void initPageObjects()
	{
		loginPage=new LoginPage(hooks.driver);
		mainPage=new MainPage(hooks.driver);
	}
	
	@When("I enter username as {string}")
	public void i_enter_username_as(String username) 
	{
		
		loginPage.enterUsername(username);
		
	}
	
	@When("I enter password as {string}")
	public void i_enter_password_as(String password) 
	{
		loginPage.enterPassword(password);
	}
	
	@When("I select language as {string}")
	public void i_select_language_as(String language) 
	{
		Select selectLan=new Select(hooks.driver.findElement(By.xpath("//select[@name='languageChoice']")));
		selectLan.selectByVisibleText(language);
	}
	@When("I click on login")
	public void i_click_on_login() 
	{
		loginPage.clickOnLogin();
	}
	
	@Then("I should get access to portal with title as {string}")
	public void i_should_get_access_to_portal_with_title_as(String expectedTitle) 
	{
		 Assert.assertEquals(mainPage.getMainPageTitle(), expectedTitle);
	}
	
	@Then("I should not get access to portal with error message as {string}")
	public void i_should_not_get_access_to_portal_with_error_message_as(String expectedError) {
		
		String actualError= loginPage.getInvalidErrorMessage();
		Assert.assertTrue(actualError.contains(expectedError));  //expect true
	}

}




