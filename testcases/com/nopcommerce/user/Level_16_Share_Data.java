package com.nopcommerce.user;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_16_Share_Data extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	
	
	String password,emailCorrect;
	
	

	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String browserName) {
		driver= getBrowserDriver(browserName);	
		homePage=PageGeneratorManager.getUserHomePage(driver);
		
		emailCorrect = Common_01_Register.emailCorrect;
		password = Common_01_Register.password;
		log.info("Login - Step 01: Navigate to Login page");

		loginPage=homePage.clickToLoginLink();
		
		log.info("Login - Step 02: Enter to Email textbox with value is'"+emailCorrect+"'");
		loginPage.inputToEmailTextbox(emailCorrect);
		
		log.info("Login - Step 03: Enter to Email textbox with value is'"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04: Click to Login button");
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
