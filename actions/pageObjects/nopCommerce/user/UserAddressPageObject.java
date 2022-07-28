package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInforPageUI;

public class UserAddressPageObject extends BasePage {
	WebDriver driver;
	public UserAddressPageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isCustomerInforPageDisplayed() {
		waitForAllElementVisible(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplay(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}

}
