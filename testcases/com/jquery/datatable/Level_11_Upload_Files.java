package com.jquery.datatable;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.uploadFile.HomePageObject;
import pageObject.jQuery.uploadFile.PageGeneratorManager;


public class Level_11_Upload_Files extends BaseTest {
	String mocchauFileName= "mocchau.jpg";
	String ninhbinhFileName= "ninhbinh.jpg";
	String sapaFileName= "sapa.jpg";
	String [] multipleFileNames = {mocchauFileName,ninhbinhFileName,sapaFileName};

	@Parameters({"browser","url"})
	
	@BeforeClass
	public void beforeClass(String browserName,String appUrl) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName,appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultiplesFiles(driver, mocchauFileName);
		
		Assert.assertTrue( homePage.isFileLoadedByName(mocchauFileName));
		
		homePage.clickToStartButton();
	
		Assert.assertTrue( homePage.isFileLinkUpLoadedByName(mocchauFileName));
		Assert.assertTrue( homePage.isFileImageUpLoadedByName(mocchauFileName));

	}
	
	@Test
	public void Upload_02_Multiple_Files_Per_Time() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadMultiplesFiles(driver, multipleFileNames);
		Assert.assertTrue( homePage.isFileLoadedByName(mocchauFileName));
		Assert.assertTrue( homePage.isFileLoadedByName(ninhbinhFileName));
		Assert.assertTrue( homePage.isFileLoadedByName(sapaFileName));

		homePage.clickToStartButton();

		Assert.assertTrue( homePage.isFileLinkUpLoadedByName(mocchauFileName));
		Assert.assertTrue( homePage.isFileLinkUpLoadedByName(ninhbinhFileName));
		Assert.assertTrue( homePage.isFileLinkUpLoadedByName(sapaFileName));

		Assert.assertTrue( homePage.isFileImageUpLoadedByName(mocchauFileName));
		Assert.assertTrue( homePage.isFileImageUpLoadedByName(ninhbinhFileName));
		Assert.assertTrue( homePage.isFileImageUpLoadedByName(sapaFileName));



	}
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
	
	private WebDriver driver;
	private HomePageObject homePage; 
}
