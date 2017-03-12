package com.testdroid.appium.android.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

import com.globalsoft.bdd.utils.Util;

import net.serenitybdd.core.pages.PageObject;

public class AndroidSubmitPage extends PageObject {
	
	public AndroidSubmitPage(WebDriver driver) {
		super(driver);
	}
	

	public void verifyNameTextBoxIsDisplayed() {
		try {
			getDriver().findElement(By.xpath(
					Util.getXpath(AndroidSubmitPage.class.getSimpleName(), "textboxName")))
			.isDisplayed();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void typingName(final String name) {
		try {
			getDriver().findElement(By.xpath(
					Util.getXpath(AndroidSubmitPage.class.getSimpleName(), "textboxName")))
			.sendKeys(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void selectingUseTestdroidCloud() {
		try {
			getDriver().findElement(By.xpath(
					Util.getXpath(AndroidSubmitPage.class.getSimpleName(), "radiobuttonUseTestdroidCloud")))
			.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clickingTheSubmitButton() {
		try {
			getDriver().navigate().back();
			getDriver().findElement(By.xpath(
					Util.getXpath(AndroidSubmitPage.class.getSimpleName(), "buttonSubmit")))
			.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
