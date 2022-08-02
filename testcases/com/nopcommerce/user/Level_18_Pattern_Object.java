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
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_18_Pattern_Object extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	
	
	String firstName,lastName,password,emailCorrect,invalidEmail,emailNotFound,date,month,year;
	
	

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
		date="28";
		month="April";
		year="1991";
	
	}
	
	@Test
	public void User_01_Register() {
		log.info("Register - Step01: Open 'Register' page");
		registerPage=homePage.clickToRegisterLink();
		
		registerPage.clickToRadioButtonByLabel(driver, "Female");
		
		log.info("Register - Step02: Enter to Firstname textbox with value is '"+firstName+"'");
		registerPage.inputToTextboxByID(driver, "FirstName",firstName);
		
		log.info("Register - Step03: Enter to Lastname textbox with value is '"+lastName+"'");
		registerPage.inputToTextboxByID(driver, "LastName",lastName);
		
		log.info("Register - Step04: Enter to date dropdown with value is '"+date+"'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthDay",date);
		
		log.info("Register - Step05: Enter to month dropdown with value is '"+month+"'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthMonth",month);
		
		log.info("Register - Step06: Enter to year dropdown with value is '"+year+"'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthYear",year);

		
		
		log.info("Register - Step07: Enter to Email textbox with value is '"+emailCorrect+"'");
		registerPage.inputToTextboxByID(driver, "Email",emailCorrect);
		
		registerPage.clickToCheckboxByLabel(driver, "Newsletter");
		
		log.info("Register - Step08: Enter to Password textbox with value is '"+password+"'");
		registerPage.inputToTextboxByID(driver, "Password",password);
		
		log.info("Register - Step09: Enter to Confirm Password textbox with value is '"+password+"'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword",password);
		
		log.info("Register - Step10: Click to 'Register' button");
		registerPage.clickToButtonByText(driver,"Register");

		log.info("Register - Step11: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
		
		log.info("Register - Step12: Click to Logout link");
		homePage=registerPage.clickToLogoutLink();

		

	}
	
	@Test
	public void User_02_Login() {
		
		log.info("Login - Step 01: Navigate to Login page");

		loginPage=homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Enter to Email textbox with value is'"+emailCorrect+"'");
		loginPage.inputToTextboxByID(driver, "Email", emailCorrect);
		
		log.info("Login - Step 03: Enter to Email textbox with value is'"+password+"'");
		loginPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage=PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void User_03_My_Account() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");
		customerInforPage = homePage.clickToMyAccountLink();
		
		log.info("My Account - Step 02: Verify 'Customer Infor' page is displayed");
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
		log.info("My Account - Step 03: Verify 'First Name' value is correctly");
		
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"FirstName"), firstName);

		log.info("My Account - Step 04: Verify 'Last Name' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"LastName"), lastName);


		log.info("My Account - Step 05: Verify 'Email' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"Email"), emailCorrect);



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
