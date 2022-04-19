package com.nopcommerce.common;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	
	private String firstName,lastName;
	public static String password,emailCorrect;
	
	@Parameters("browser")
	@BeforeTest(description ="Create new common User for all Classes Test")
	public void Register(String browserName ) {
		
		driver= getBrowserDriver(browserName);
		homePage=PageGeneratorManager.getUserHomePage(driver);
		
		password="123456";
		firstName ="nhut";
		lastName="minh";
		emailCorrect="nhuttest"+generateNumber()+"@yopmail.com";
		
		log.info("Register - Step01: Open 'Register' page");
		registerPage=homePage.clickToRegisterLink();
		log.info("Register - Step02: Enter to Firstname textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step03: Enter to Lastname textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step04: Enter to Email textbox with value is '"+emailCorrect+"'");
		registerPage.inputToEmailTextbox( emailCorrect);
		
		log.info("Register - Step04: Enter to Password textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - Step06: Enter to Confirm Password textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);		
		
		log.info("Register - Step07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		log.info("Register - Step09: Click to Logout link");
		homePage=registerPage.clickToLogoutLink();

		

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
