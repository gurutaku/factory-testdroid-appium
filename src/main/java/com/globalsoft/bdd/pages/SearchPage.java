package com.globalsoft.bdd.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.globalsoft.bdd.utils.Util;

import net.serenitybdd.core.pages.PageObject;

public class SearchPage extends PageObject {
	
	public SearchPage(final WebDriver driver) {
		super(driver);
	}
	
	public void typingTextBoxSearch(final String searchContent) {
		try {
			WebElement element = getDriver().findElement(By.xpath(
					Util.getXpath(SearchPage.class.getSimpleName(), "textboxSearch")));
			element.sendKeys(searchContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clickingButtonNoThanks() {
		try {
			WebElement element = getDriver().findElement(By.xpath(
					Util.getXpath(SearchPage.class.getSimpleName(), "buttonNoThanks")));
			element.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clickingButtonSearch() {
		try {
			WebElement element = getDriver().findElement(By.xpath(
					Util.getXpath(SearchPage.class.getSimpleName(), "buttonSearch")));
			element.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
