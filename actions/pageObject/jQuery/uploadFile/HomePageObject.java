package pageObject.jQuery.uploadFile;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.uploadFile.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
		return isElementDisplay(driver, HomePageUI.FILE_NAME_LOADED, fileName);
	}

	public boolean isFileLinkUpLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_UP_LOADED, fileName);
		return isElementDisplay(driver, HomePageUI.FILE_NAME_UP_LOADED, fileName);
	}
	
	public void clickToStartButton() {
		 List <WebElement> startButtons= getListWebElement(driver, HomePageUI.START_BUTTON);
		 for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}
			
	}

	public boolean isFileImageUpLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_UP_LOADED_IMAGE, fileName);
		return isImageLoaded(driver, HomePageUI.FILE_NAME_UP_LOADED_IMAGE, fileName);
	}

}
