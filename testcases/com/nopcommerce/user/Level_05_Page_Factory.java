package com.nopcommerce.user;
import java.util.Random;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;


public class Level_05_Page_Factory extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)
	
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	
	String firstName,lastName,password,emailCorrect,invalidEmail,emailNotFound;
	
	

	@Parameters("browser")
	
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName);
		System.out.println("TestCase page object"+driver.toString());
		//Initial (khởi tạo)
		//che giấu việc khởi tạo của 1 đối tượng 
		homePage=new HomePageObject(driver);
		registerPage=new RegisterPageObject(driver);
		password="123456";
		firstName ="nhut";
		lastName="minh";
		emailCorrect="nhuttest"+generateNumber()+"@yopmail.com";
		emailNotFound="nhutlatao"+generateNumber()+"@yopmail.com";
		invalidEmail="qwe2312@@yopmail.com";
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();


		System.out.println("Register Page -Step02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailCorrect);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
		
		registerPage.clickToLogoutLink();
	}
	
	@Test
	public void Login_01_Empty_Data() {
		
		System.out.println("Home Page -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		loginPage=new LoginPageObject(driver);
		System.out.println("Login Page -Step02:Click to Login Button");

		loginPage.clickToLoginButton();
		

		
		System.out.println("Login Page -Step03:Verify error message displayed");

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Please enter your email");
		
		
	}
	
	@Test
	public void Login_02_Invalid_Email() {

		System.out.println("Login Page_02  -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page_02  -Step02:Login with invalid email");
		
		loginPage=new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page_02 -Step03:Login with invalid email");
		
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(),"Wrong email");


	}
	
	@Test
	public void Login_03_Email_Not_Register() {

		
		System.out.println("Login Page_03  -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page_03  -Step02:Login with invalid email");
		loginPage=new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(emailNotFound);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page_03 -Step03:Login with invalid email");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");


	}
	
	@Test
	public void Login_04_Existing_Email_Passwor_Null() {

		System.out.println("Login Page_04  -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page_04  -Step02:Login with invalid email");
		
		loginPage=new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(emailCorrect);
		loginPage.inputToPasswordTextbox("");

		System.out.println("Login Page_04 -Step03:Login with correct email and password null");
		
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	@Test
	public void Login_05_Existing_Email_Passwor_Incorrect() {
		System.out.println("Login Page_05  -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page_05  -Step02:Login with invalid email");
		loginPage=new LoginPageObject(driver);

		loginPage.inputToEmailTextbox(emailCorrect);
		loginPage.inputToPasswordTextbox("123");

		System.out.println("Login Page_05 -Step03:Login with correct email and password incorrect");
		loginPage.clickToLoginButton();


		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	
	
	@Test
	public void Login_06_Login_Success() {
		
		System.out.println("Login Page_06  -Step01:Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page_06  -Step02:Login with correct email");
		loginPage=new LoginPageObject(driver);


		loginPage.inputToEmailTextbox(emailCorrect);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
		
		System.out.println("Login Page_06 -Step03:Verify Login Success");
		homePage =new HomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
