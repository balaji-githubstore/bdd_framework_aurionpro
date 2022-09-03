package com.aurionpro.steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aurionpro.base.AutomationHooks;
import com.aurionpro.base.DataTransfer;
import com.aurionpro.pages.MainPage;
import com.aurionpro.pages.SearchOrAddPatientPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
	
public class PatientSteps {
	
	
	private AutomationHooks hooks;
	private DataTransfer dt;
	
	private MainPage mainPage;
	private SearchOrAddPatientPage sapPage;
	
	private static String actualAlertText;
	
	public PatientSteps(AutomationHooks hooks,DataTransfer dt)
	{
		this.hooks=hooks;
		this.dt=dt;
		initPageObjects();
//		System.out.println(hooks.driver);
//		System.out.println(d.width);
//		System.out.println(d.height);
	}

	
	public void initPageObjects()
	{
		mainPage=new MainPage(hooks.driver);
		sapPage=new SearchOrAddPatientPage(hooks.driver);
	}
	
	@When("I click on Patient Menu")
	public void i_click_on_patient_menu() {
		mainPage.clickOnPatient();
	}

	@When("I click on New Search Menu")
	public void i_click_on_new_search_menu() {
		hooks.driver.findElement(By.xpath("//div[text()='New/Search']")).click();
	}

	@When("I fill the who section form")
	public void i_fill_the_who_section_form(DataTable dataTable) {

		System.out.println(dataTable);
		
		List<Map<String,String>> ls= dataTable.asMaps();
		
		System.out.println(ls.get(0));
		
		String firstName=ls.get(0).get("firstname");
		String lastName=ls.get(0).get("lastname");
		String dateOfBirth=ls.get(0).get("dob");
		String gender=ls.get(0).get("gender");
		
		hooks.driver.switchTo().frame(hooks.driver.findElement(By.xpath("//iframe[@name='pat']")));
		
		sapPage.enterFirstName(firstName);
		hooks.driver.findElement(By.id("form_lname")).sendKeys(lastName);
		hooks.driver.findElement(By.id("form_DOB")).sendKeys(dateOfBirth);
		
		Select selectGender=new Select(hooks.driver.findElement(By.id("form_sex")));
		selectGender.selectByVisibleText(gender);
		
	}

	@When("I click on create new patient")
	public void i_click_on_create_new_patient() {
		hooks.driver.findElement(By.id("create")).click();
		hooks.driver.switchTo().defaultContent();
	}

	@When("I click on confirm create new patient")
	public void i_click_on_confirm_create_new_patient() {
		hooks.driver.switchTo().frame(hooks.driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		hooks.driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		hooks.driver.switchTo().defaultContent();
	}
	

	@When("I store the alert text and handle it")
	public void i_store_the_alert_text_and_handle_it() {
		
		WebDriverWait wait=new WebDriverWait(hooks.driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.alertIsPresent());
		
		actualAlertText=hooks.driver.switchTo().alert().getText();

		hooks.driver.switchTo().alert().accept();
	}

	@When("I close the happy birthday if available")
	public void i_close_the_happy_birthday_if_available() {
		
		//check presence of element
		if(hooks.driver.findElements(By.xpath("//div[@class='closeDlgIframe']")).size()>0)
		{
			hooks.driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();
		}
		
	}

	@Then("I should verify the alert text contains {string}")
	public void i_should_verify_the_alert_text_contains(String expectedAlertText) {
		Assert.assertTrue(actualAlertText.contains(expectedAlertText));
	}

	@Then("I should get the added patient details as {string}")
	public void i_should_get_the_added_patient_details_as(String expectedPatient) {
		hooks.driver.switchTo().frame(hooks.driver.findElement(By.xpath("//iframe[@name='pat']")));
		String actualText=hooks.driver.findElement(By.xpath("//h2[contains(text(),'Medical Record')]")).getText().trim();
		Assert.assertEquals(actualText, expectedPatient);
	}

}










