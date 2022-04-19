package com.nopcommerce.user;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_16_Share_Data_B extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	
	String firstName,lastName,password,emailCorrect;
	
	

	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver= getBrowserDriver(browserName);
		homePage=PageGeneratorManager.getUserHomePage(driver);
		password="123456";
		firstName ="nhut";
		lastName="minh";
		emailCorrect="nhuttest"+generateNumber()+"@yopmail.com";
		
		log.info("Pre-Condition - Step01: Open 'Register' page");
		registerPage=homePage.clickToRegisterLink();
		log.info("Pre-Condition  - Step02: Enter to Firstname textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition  - Step03: Enter to Lastname textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition  - Step04: Enter to Email textbox with value is '"+emailCorrect+"'");
		registerPage.inputToEmailTextbox( emailCorrect);
		
		log.info("Pre-Condition  - Step04: Enter to Password textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition  - Step06: Enter to Confirm Password textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);		
		
		log.info("Pre-Condition  - Step07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition  - Step08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
		
		log.info("Pre-Condition  - Step09: Click to Logout link");
		homePage=registerPage.clickToLogoutLink();
		
	
		log.info("Pre-Condition  - Step 10: Navigate to Login page");

		loginPage=homePage.clickToLoginLink();
		
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is'"+emailCorrect+"'");
		loginPage.inputToEmailTextbox(emailCorrect);
		
		log.info("Pre-Condition - Step 12: Enter to Email textbox with value is'"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 13: Click to Login button");
		loginPage.clickToLoginButton();
	
	}
	@Test
	public void Search_01_Empty_Data() {
		
	
	}
	@Test
	public void Search_02_Relative_Product_Name() {
		
		
	}
	@Test
	public void Search_03_Absolute_Product_Name() {
		
		
	}
	@Test
	public void Search_04_Parent_Category() {
		
		
	}
	@Test
	public void Search_05_Incorrect_Manufactorer() {
		
		
	}
	@Test
	public void Search_06_Correct_Manufactorer() {
		
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
}
