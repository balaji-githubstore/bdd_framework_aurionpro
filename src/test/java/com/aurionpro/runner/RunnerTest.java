package com.aurionpro.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
//		features = {"src/test/resources/Feature/Login.feature","src/test/resources/Feature/Patient.feature"}
		features = {"src/test/resources/Feature"}
		,glue = {"com.aurionpro.steps","com.aurionpro.base"}
		,monochrome = true
	    ,publish = false
	    ,plugin = {"html:target/cucumber-report.html","json:target/cucumber-report.json"}
		,dryRun = false
		,tags = "@addpatient"
//		,tags = "@login and (not @invalid)"
		)

public class RunnerTest extends AbstractTestNGCucumberTests {

}
