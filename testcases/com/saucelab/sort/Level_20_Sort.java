package com.saucelab.sort;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.saucelab.InventoryPageObject;
import pageObjects.saucelab.LoginPageObject;
import pageObjects.saucelab.PageGeneratorManager;

public class Level_20_Sort extends BaseTest {
	WebDriver driver;
	//Declare (khai báo)	
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;
	

	@Parameters({"browser","url"})
	
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver=getBrowserDriver(browserName, appUrl);
		
		loginPage=PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage=loginPage.clickToLoginButton();

	
	
	}
	
	@Test
	public void Sort_01_Name() {
		log.info("Register - Step01: Open 'Register' page");
		inventoryPage.selectItemInSortDropdown("Name (A to Z)");
		
		verifyTrue(inventoryPage.isProductNameSortAscending());
		
		inventoryPage.selectItemInSortDropdown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortDescending());

	}
	
	@Test
	public void Sort_02_Price() {
		
		inventoryPage.selectItemInSortDropdown("Price (low to high)");
		verifyTrue(inventoryPage.isProductPriceSortAscending());

		inventoryPage.selectItemInSortDropdown("Price (high to low)");
		verifyTrue(inventoryPage.isProductPriceSortDescending());

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
