package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;
	
	public AdminDashboardPageObject (WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isDashboardHeaderDisplayed() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplay(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}

}
