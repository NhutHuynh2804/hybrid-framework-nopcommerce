package pageObjects.saucelab;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucelab.InventoryPageUI;

public class InventoryPageObject extends BasePage {
	WebDriver driver;
	
	public InventoryPageObject(WebDriver driver) {
		this.driver=driver;
	}

	public void selectItemInSortDropdown(String itemText) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
		
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNames= getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText= new ArrayList<String>();
		
		for (WebElement productName : productNames) {
			productNameText.add(productName.getText());
		}
		
		System.out.println("Before sort Asc:---------------");
		for (String product : productNameText) {
			System.out.println(product);

		}
		
		List<String> productNameTextClone= new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		
		System.out.println("After sort Asc:---------------");
		for (String product : productNameTextClone) {
			System.out.println(product);

		}
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
        List<WebElement> productNames= getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText= new ArrayList<String>();
		
		for (WebElement productName : productNames) {
			productNameText.add(productName.getText());
		}
		
		System.out.println("Before sort Des:---------------");
		for (String product : productNameText) {
			System.out.println(product);

		}
		
		List<String> productNameTextClone= new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		
		System.out.println("After sort Des:---------------");
		for (String product : productNameTextClone) {
			System.out.println(product);

		}
		
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		
        List<WebElement> productPrices= getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue= new ArrayList<Float>();
		
		for (WebElement productPrice : productPrices) {
			productPriceValue.add(Float.parseFloat( productPrice.getText().replace("$","")));
		}
		
		System.out.println("Before sort Asc:---------------");
		for (Float productPrice : productPriceValue) {
			System.out.println(productPrice);

		}
		
		List<Float> productPriceTextClone= new ArrayList<Float>(productPriceValue);
		Collections.sort(productPriceTextClone);
		
		System.out.println("After sort Asc:---------------");
		for (Float productPrice : productPriceTextClone) {
			System.out.println(productPrice);

		}
		
		return productPriceValue.equals(productPriceTextClone);
		
		
		
	}

	public boolean isProductPriceSortDescending() {
		 List<WebElement> productPrices= getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
			
			List<Float> productPriceValue= new ArrayList<Float>();
			
			for (WebElement productPrice : productPrices) {
				productPriceValue.add(Float.parseFloat( productPrice.getText().replace("$","")));
			}
			
			System.out.println("Before sort Asc:---------------");
			for (Float productPrice : productPriceValue) {
				System.out.println(productPrice);

			}
			
			List<Float> productPriceTextClone= new ArrayList<Float>(productPriceValue);
			Collections.sort(productPriceTextClone);
			Collections.reverse(productPriceTextClone);
			
			System.out.println("After sort Asc:---------------");
			for (Float productPrice : productPriceTextClone) {
				System.out.println(productPrice);

			}
			
			return productPriceValue.equals(productPriceTextClone);
	}

	

}
