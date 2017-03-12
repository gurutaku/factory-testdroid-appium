package com.testdroid.appium.android.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.webdriver.DriverSource;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.testdroid.api.http.MultipartFormDataContent;
//import com.testdroid.appium.android.utils.BaseTest;
//import com.testdroid.appium.android.utils.BaseTest.AppiumResponse;

public class MyAndroidDriver implements DriverSource {
    private static final String TARGET_APP_PATH = "../../../apps/builds/BitbarSampleApp.apk";
    private static final String TESTDROID_SERVER = "http://appium.testdroid.com";
    private static int counter;
    
    @Override
    public WebDriver newDriver() {
    	try {
	        counter = 0;
	        Map<String, String> env = System.getenv();
	        String testdroid_apikey = "8hBpWvFruNrGcLHQ6mXn4kbomxHOt2jC";
	        //String testdroid_apikey = env.get("TESTDROID_APIKEY");
	
	
	        if(StringUtils.isEmpty(testdroid_apikey)) {
	            throw new IllegalArgumentException("Missing TESTDROID_APIKEY environment variable");
	        }
	
	        String fileUUID = uploadFile(TARGET_APP_PATH, TESTDROID_SERVER, testdroid_apikey);
	
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("testdroid_username", "guru.taku@gmail.com");
	        capabilities.setCapability("testdroid_password", "diediedie");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("testdroid_target", "Android");
	        capabilities.setCapability("deviceName", "Android Device");
	        
	        //capabilities.setCapability("testdroid_apiKey", testdroid_apikey);
	        
	        capabilities.setCapability("testdroid_project", "LocalAppium");
	        capabilities.setCapability("testdroid_testrun", "Android Run 1");
	        
	        // See available devices at: https://cloud.testdroid.com/#public/devices
	        capabilities.setCapability("testdroid_device", "LG Google Nexus 5 6.0.1 -US"); // Freemium device
	        capabilities.setCapability("testdroid_app", fileUUID); //to use existing app using "latest" as fileUUID
	
	        // Optional
	        //capabilities.setCapability("testdroid_description", "Appium project description");
	        //capabilities.setCapability("platformVersion", "4.4.2");
	        //capabilities.setCapability("app-activity", ".BitbarSampleApplicationActivity");
	        //capabilities.setCapability("app", "com.bitbar.testdroid");
	        
	        System.out.println("Capabilities:" + capabilities.toString());
	
	        System.out.println("Creating Appium session, this may take couple minutes..");
	        return new AndroidDriver(new URL(TESTDROID_SERVER+"/wd/hub"), capabilities);
    	}
    	catch (IOException e) {
    		throw new Error(e);
    	}
    }
    @Override
    public boolean takesScreenshots() {
        return true;
    }
    
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static int screenshotCounter;

    public static String uploadFile(String targetAppPath, String serverURL, String testdroid_apikey) throws IOException {
        final HttpHeaders headers = new HttpHeaders().setBasicAuthentication(testdroid_apikey, "");

        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                        request.setHeaders(headers);
                    }


                });
        MultipartFormDataContent multipartContent = new MultipartFormDataContent();
        FileContent fileContent = new FileContent("application/octet-stream", new File(targetAppPath));

        MultipartFormDataContent.Part filePart = new MultipartFormDataContent.Part("file", fileContent);
        multipartContent.addPart(filePart);

        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(serverURL+"/upload"), multipartContent);

        HttpResponse response = request.execute();
        System.out.println("response:" + response.parseAsString());

        AppiumResponse appiumResponse = request.execute().parseAs(AppiumResponse.class);
        System.out.println("File id:" + appiumResponse.uploadStatus.fileInfo.file);

        return appiumResponse.uploadStatus.fileInfo.file;

    }

    public static void takeScreenshot(String screenshotName, WebDriver driver) {
    	screenshotCounter = screenshotCounter + 1;
        String fullFileName = System.getProperty("user.dir") + "/Screenshots/" + getScreenshotsCounter() + "_" + screenshotName + ".png";

        screenshot(fullFileName, driver);
    }

    private static File screenshot(String name, WebDriver driver) {
        System.out.println("Taking screenshot...");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            File testScreenshot = new File(name);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getScreenshotsCounter() {
        if (screenshotCounter < 10) {
            return "0" + screenshotCounter;
        } else {
            return String.valueOf(screenshotCounter);
        }
    }

    public static class AppiumResponse {
        Integer status;
        @Key("sessionId")
        String sessionId;

        @Key("value")
        UploadStatus uploadStatus;

    }

    public static class UploadedFile {
        @Key("file")
        String file;
    }

    public static class UploadStatus {
        @Key("message")
        String message;
        @Key("uploadCount")
        Integer uploadCount;
        @Key("expiresIn")
        Integer expiresIn;
        @Key("uploads")
        UploadedFile fileInfo;
    }
}
