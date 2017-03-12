package com.globalsoft.bdd.acceptancetest.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import com.globalsoft.bdd.pages.SearchPage;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class SearchSteps extends ScenarioSteps{

	private SearchPage searchPage;
	
	public SearchSteps(final Pages pages) throws ClassNotFoundException{
		super(pages);
		searchPage = getPages().get(SearchPage.class);
	}
	
	@Given("landed on the Main page")
	public void landedOnGoogleSearchPage() {
		searchPage.open();
	}
	
	@When("typing >$searchContent")
	public void typingSearchContent(final String searchContent) {
		//searchPage.clickingButtonNoThanks();
		searchPage.typingTextBoxSearch(searchContent);
	}
	
	@When("clicking the search button")
	public void clickingTheSearchButton() {
		searchPage.clickingButtonSearch();
	}
	
}
