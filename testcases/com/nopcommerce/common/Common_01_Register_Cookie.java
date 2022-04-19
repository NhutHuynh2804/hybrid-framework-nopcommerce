package com.nopcommerce.common;
import java.util.Random;
import java.util.Set;

import pageObjects.nopCommerce.user.UserLoginPageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_Cookie extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> LoggedCookies;

	
	
	private String firstName,lastName;
	private String password,emailCorrect;
	
	@Parameters("browser")
	@BeforeTest(description ="Create new common User for all Classes Test")
	public void Register(String browserName ) {
		
		driver= getBrowserDriver(browserName);
		homePage=PageGeneratorManager.getUserHomePage(driver);
		
		password="123456";
		firstName ="nhut";
		lastName="minh";
		emailCorrect="nhuttest"+generateNumber()+"@yopmail.com";
		
		log.info("Pre-Condition - Step01: Open 'Register' page");
		registerPage=homePage.clickToRegisterLink();
		log.info("Pre-Condition - Step02: Enter to Firstname textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - Step03: Enter to Lastname textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition - Step04: Enter to Email textbox with value is '"+emailCorrect+"'");
		registerPage.inputToEmailTextbox( emailCorrect);
		
		log.info("Pre-Condition - Step04: Enter to Password textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step06: Enter to Confirm Password textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);		
		
		log.info("Pre-Condition - Step07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition - Step08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		log.info("Pre-Condition - Step09: Click to Logout link");
		homePage=registerPage.clickToLogoutLink();
		
		log.info("Pre-Condition  - Step 10: Navigate to Login page");

		loginPage=homePage.clickToLoginLink();
		
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is'"+emailCorrect+"'");
		loginPage.inputToEmailTextbox(emailCorrect);
		
		log.info("Pre-Condition - Step 12: Enter to Email textbox with value is'"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 13: Click to Login button");
		loginPage.clickToLoginButton();
		
		LoggedCookies = homePage.getAllCookies(driver);
		
		for (Cookie cookie : LoggedCookies) {
			System.out.println("Cookie at Common Class:"+cookie);
		} 

	}
	

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
	
	public int generateNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
}
