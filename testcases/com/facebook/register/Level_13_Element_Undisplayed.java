package com.facebook.register;
import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;


public class Level_13_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;
	

	@Parameters({"browser","url"})
	
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName,appUrl);
		
		loginPage=PageGeneratorManager.getLoginPage(driver);
		
	
	}
	
	@Test
	public void User_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());

	}
	
	@Test
	public void User_02_Verify_Element_Undisplayed_In_Dom() {
		
		loginPage.enterToEmailTextbox("test@yopmail.com");
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		loginPage.enterToEmailTextbox("");
		
		loginPage.sleepInSecond(2);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}
	
	@Test
	public void User_03_Verify_Element_Undisplayed_Not_In_Dom() {
		
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(2);
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());

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
