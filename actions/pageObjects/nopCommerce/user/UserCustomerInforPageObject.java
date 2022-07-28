package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInforPageUI;

public class UserCustomerInforPageObject extends BasePage {
	WebDriver driver;
	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isCustomerInforPageDisplayed() {
		waitForAllElementVisible(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplay(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}
	
	public void updateInforAtCustomerPage(WebDriver driver, String value , String field ) {
		waitForElementVisible(driver, CustomerInforPageUI.DYNAMIC_INPUT_FIELD_AT_CUSTOMER_PAGE,field );
		sendkeyToElement(driver, CustomerInforPageUI.DYNAMIC_INPUT_FIELD_AT_CUSTOMER_PAGE, value, field);
	}
	
	public void updateGender(WebDriver driver, String gender) {
		waitForElementClickable(driver, CustomerInforPageUI.GENDRER_RADIO_BUTTON,gender);
		clickToElement(driver, CustomerInforPageUI.GENDRER_RADIO_BUTTON, gender);
	}

	public void clickSave() {
		waitForElementClickable(driver, CustomerInforPageUI.BUTTON_SAVE);
		clickToElement(driver, CustomerInforPageUI.BUTTON_SAVE);
	}
	
	public void updateDateOfBirth(WebDriver driver, String valueOfBirth , String fieldOfBirth) {
		waitForElementVisible(driver, CustomerInforPageUI.INPUT_DATE_OF_BIRTH, fieldOfBirth);
		selectItemInDefaultDropdown(driver, CustomerInforPageUI.INPUT_DATE_OF_BIRTH, valueOfBirth, fieldOfBirth);
	}

	

}
