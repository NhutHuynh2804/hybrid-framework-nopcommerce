package com.nopcommerce.user;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_17_Custom_Close_Driver extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
	
	String firstName,lastName,password,emailCorrect,invalidEmail,emailNotFound;
	
	

	@Parameters("browser")
	
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName);
		System.out.println("TestCase page object"+driver.toString());
	
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
		
		driver = null;

		log.info("Register - Step08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
		
		log.info("Register - Step09: Click to Logout link");
		homePage=registerPage.clickToLogoutLink();
	
	}
	
	@Test
	public void User_01_Register() {
		
		

	}
	
	@Test
	public void User_02_Login() {
		
		
	}
	
	
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	public int generateNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
}
