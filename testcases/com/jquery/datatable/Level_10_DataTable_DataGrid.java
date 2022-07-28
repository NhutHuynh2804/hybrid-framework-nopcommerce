package com.jquery.datatable;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.dataTable.HomePageObject;
import pageObject.jQuery.dataTable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;

	
	
	

	@Parameters({"browser","url"})
	
	@BeforeClass
	public void beforeClass(String browserName,String appUrl) {
		System.out.println("Run on"+browserName);
		driver= getBrowserDriver(browserName,appUrl);
		homePage =PageGeneratorManager.getHomePage(driver);

	
	}
	
	
	public void Table_01_Paging() {
		
		homePage.openPagingByPageNumber("10");
		Assert.assertTrue(homePage.isPageNumberActivated("10"));
		
		homePage.openPagingByPageNumber("20");
		Assert.assertTrue(homePage.isPageNumberActivated("20"));

		homePage.openPagingByPageNumber("6");
		Assert.assertTrue(homePage.isPageNumberActivated("6"));

		homePage.openPagingByPageNumber("9");
		Assert.assertTrue(homePage.isPageNumberActivated("9"));

	}
	
	
	public void Table_02_Enter_To_Header() {
		homePage.enterToHeaderTextboxByLabel("Country", "VietNam");
		homePage.enterToHeaderTextboxByLabel("Females", "BBBBB");
		homePage.enterToHeaderTextboxByLabel("Males", "CCCCCC");
		homePage.enterToHeaderTextboxByLabel("Total", "1991");

		
	}
	
	
	public void Table_03_Enter_To_Header() {
		actualAllCountryValues=homePage.getValueEachRowAtAllPage();
	
	}
	
	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(2);
		homePage.enterToTextboxByColumNameAtRowNumber("Album","1", "Nhut Huynh");
		homePage.enterToTextboxByColumNameAtRowNumber("Artist","2", "Tra Phuong");
		homePage.enterToTextboxByColumNameAtRowNumber("Year","5", "1997");
		homePage.enterToTextboxByColumNameAtRowNumber("Price","1", "25");
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin","3","Japan");
		homePage.sleepInSecond(1);
		homePage.selectDropdownByColumnNameAtRowNumber("Origin","4","US");
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?","3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?","5");

		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?","1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?","2");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?","4");
		
		homePage.clickToButtonIconByRowNumber("5","Remove Current Row");
		homePage.clickToButtonIconByRowNumber("4","Remove Current Row");
		homePage.clickToButtonIconByRowNumber("3","Remove Current Row");
		homePage.clickToButtonIconByRowNumber("2","Remove Current Row");
		homePage.clickToButtonIconByRowNumber("1","Remove Current Row");



		
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
