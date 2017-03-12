package com.testdroid.appium.android.acceptancetest.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import com.testdroid.appium.android.pages.AndroidSubmitPage;

import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class AndroidSubmitSteps extends ScenarioSteps{

	private AndroidSubmitPage androidSubmitPage;
	
	public AndroidSubmitSteps(final Pages pages) throws ClassNotFoundException{
		super(pages);
		androidSubmitPage = getPages().get(AndroidSubmitPage.class);
	}
	
	@Given("landed on the main page")
	public void verifyNameTextBoxIsDisplayed() {
		androidSubmitPage.verifyNameTextBoxIsDisplayed();
	}
	
	@When("selecting Use Testdroid Cloud")
	public void selectingUseTestdroidCloud() {
		androidSubmitPage.selectingUseTestdroidCloud();
	}
	
	@When("typing >$name")
	public void typingName(final String name) {
		//searchPage.clickingButtonNoThanks();
		androidSubmitPage.typingName(name);
		//MyAndroidDriver.takeScreenshot("after_adding_name", getDriver());
	}
	
	@When("clicking the submit button")
	public void clickingTheSubmitButton() {
		androidSubmitPage.clickingTheSubmitButton();
		//MyAndroidDriver.takeScreenshot("after_answer", getDriver());
	}
	

	
}
