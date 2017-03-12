package com.globalsoft.bdd.acceptancetest;


import net.serenitybdd.jbehave.SerenityStories;




public class AcceptanceTestSuite extends SerenityStories  {
	public AcceptanceTestSuite() {
		
			findStoriesCalled("webStoriesGoogleSearch.story");
		
	}
}
