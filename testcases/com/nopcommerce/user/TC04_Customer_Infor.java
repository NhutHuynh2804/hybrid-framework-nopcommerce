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

public class TC04_Customer_Infor extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

	
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
	public void User_01_Register() {
		
		registerPage=homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox( emailCorrect);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		homePage=registerPage.clickToLogoutLink();

	}
	
	@Test
	public void User_02_Login() {

		loginPage=homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailCorrect);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

	}
	
	@Test
	public void User_03_Customer_Infor() {
		customerInforPage=homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());

	}
	
	@Test
	public void User_04_updte_Customer_Infor() {
		customerInforPage.updateGender(driver, "Male");
		customerInforPage.updateInforAtCustomerPage(driver, "Auto", "FirstName");
		customerInforPage.updateInforAtCustomerPage(driver, "FC", "LastName");
		customerInforPage.updateInforAtCustomerPage(driver, "test1122@yopmail.com", "Email");
		customerInforPage.updateInforAtCustomerPage(driver, "QAVN", "Company");
		customerInforPage.updateDateOfBirth(driver, "1", "DateOfBirthDay");
		customerInforPage.updateDateOfBirth(driver, "5", "DateOfBirthMonth");
		customerInforPage.updateDateOfBirth(driver, "1992", "DateOfBirthYear");
		customerInforPage.clickSave();

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
