package com.testdroid.appium.android.acceptancetest;


import net.serenitybdd.jbehave.SerenityStories;



public class AcceptanceTestSuite extends SerenityStories {


    public void AcceptanceTestSuite() {
    	//wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
    	findStoriesCalled("androidStoriesMainscreenSubmit.story");
        //wd.findElement(By.xpath("//android.widget.RadioButton[@text='Use Testdroid Cloud']")).click();
        //wd.findElement(By.xpath("//android.widget.EditText[@resource-id='com.bitbar.testdroid:id/editText1']")).sendKeys("John Doe");
        //takeScreenshot("after_adding_name");
        //wd.navigate().back();
        //wd.findElement(By.xpath("//android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]")).click();
        //takeScreenshot("after_answer");
    }

}
