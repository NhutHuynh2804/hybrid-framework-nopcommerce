package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject UserRegisterPage;
	private UserLoginPageObject UserloginPage;
	private AdminLoginPageObject adminLoginPage;
	private UserCustomerInforPageObject userCustomerInfoPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	String firstName,lastName,password,emailCorrect,emailAdmin,passwordAdmin;
	
	

	@Parameters("browser")
	
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName);
		System.out.println("TestCase page object"+driver.toString());
	
		userHomePage=PageGeneratorManager.getUserHomePage(driver);
		password="123456";
		firstName ="nhut";
		lastName="minh";
		emailCorrect="nhuttest"+generateNumber()+"@yopmail.com";
		emailAdmin="admin@yourstore.com";
		passwordAdmin="admin";
	
	}
	
	@Test
	public void Role_01_Register_User() {
		
		UserRegisterPage=userHomePage.clickToRegisterLink();
		UserRegisterPage.inputToFirstnameTextbox(firstName);
		UserRegisterPage.inputToLastnameTextbox(lastName);
		UserRegisterPage.inputToEmailTextbox( emailCorrect);
		UserRegisterPage.inputToPasswordTextbox(password);
		UserRegisterPage.inputToConfirmPasswordTextbox(password);		
		UserRegisterPage.clickToRegisterButton();

		Assert.assertEquals(UserRegisterPage.getRegisterSuccessMessage(),"Your registration completed");
		
		userHomePage=UserRegisterPage.clickToLogoutLink();

	}
	
	@Test
	public void Role_02_User_To_Admin() {

		UserloginPage=userHomePage.clickToLoginLink();
		userHomePage=UserloginPage.loginAsUser(emailCorrect, password);
		
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		userCustomerInfoPage= userHomePage.clickToMyAccountLink();
		
		userHomePage=userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		
		userHomePage.openHomePageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);

		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage=adminLoginPage.loginAsAdmin(emailAdmin, passwordAdmin);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

		adminLoginPage=adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	}
	
	@Test
	public void Role_03_Admin_To_User() {
		adminLoginPage.openHomePageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		
		userHomePage=PageGeneratorManager.getUserHomePage(driver);
		
		UserloginPage=userHomePage.clickToLoginLink();
		userHomePage=UserloginPage.loginAsUser(emailCorrect, password);
		
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
//		userHomePage.openHomePageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
//		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
//		adminDashboardPage=adminLoginPage.loginAsAdmin(emailAdmin, passwordAdmin);
//		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
//		adminLoginPage=adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	

	}
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
