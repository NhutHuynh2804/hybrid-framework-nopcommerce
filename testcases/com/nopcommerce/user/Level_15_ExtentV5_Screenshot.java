package com.nopcommerce.user;
import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_15_ExtentV5_Screenshot extends BaseTest {
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
	
	}
	
	@Test
	public void User_01_Register(Method method) {
	ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
	registerPage = homePage.clickToRegisterLink();

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
	registerPage.inputToFirstnameTextbox(firstName);

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
	registerPage.inputToLastnameTextbox(lastName);

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '" + emailCorrect + "'");
	registerPage.inputToEmailTextbox(emailCorrect);

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
	registerPage.inputToPasswordTextbox(password);

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
	registerPage.inputToConfirmPasswordTextbox(password);

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
	registerPage.clickToRegisterButton();

	ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
	Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login(Method method) {
	ExtentTestManager.startTest(method.getName(), "Login to system successfully");
	ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
	homePage = registerPage.clickToLogoutLink();
	loginPage = homePage.clickToLoginLink();

	ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailCorrect + "'");
	loginPage.inputToEmailTextbox(emailCorrect);

	ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
	loginPage.inputToPasswordTextbox(password);

	ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Login button");
	homePage = loginPage.clickToLoginButton();

	ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
	Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

	
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
