package com.nopcommerce.user;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object {
	WebDriver driver;
	//Declare (khai báo)
	BasePage basePage;
	String projectPath= System.getProperty("user.dir");
	
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	
	String firstName,lastName,password,emailAddress;
	
	

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		System.out.println("TestCase page object"+driver.toString());
		//Initial (khởi tạo)
		//che giấu việc khởi tạo của 1 đối tượng 
		emailAddress="nhuttest"+generateNumber()+"@yopmail.com";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage=new UserHomePageObject(driver);
		registerPage=new UserRegisterPageObject(driver);
		firstName ="nhut";
		lastName="minh";
		password="123456";
		emailAddress="nhuttest"+generateNumber()+"@yopmail.com";
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();
		
	

		System.out.println("Home Page -Step02:Click to Register Button");

		registerPage.clickToRegisterButton();
		

		
		System.out.println("Register Page -Step03:Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"Password is required.");
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {

		
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register Page -Step02:Input to required fields");
		

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( "123454@@@");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);



		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Wrong email");

		System.out.println("Register Page -Step04:Verify error message error dislayed");

	}
	
	@Test
	public void TC_03_Register_Success() {

		
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();


		System.out.println("Register Page -Step02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page -Step04:Verify success message dislayed");

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		System.out.println("Register Page -Step05:Click to logout link");

		registerPage.clickToLogoutLink();


	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();


		System.out.println("Register Page -Step02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
	
		System.out.println("Register Page -Step04:Verify Existing Message Display");

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();


		System.out.println("Register Page -Step02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page -Step04:Verify Error Message Password Display");

		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");

	}
	
	
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Home Page -Step01:Click to Register link");
		homePage.clickToRegisterLink();


		System.out.println("Register Page -Step02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(emailAddress);

		System.out.println("Register Page -Step03:Click to Register Button");

		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page -Step04:Verify Error Message Confirm Password Display");

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");

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
