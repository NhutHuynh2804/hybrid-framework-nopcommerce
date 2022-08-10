package pageObject.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.BasePageUI;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPO extends BasePage {
	 WebDriver driver;
	
	public MyInfoPO (WebDriver driver) {
		this.driver=driver;
	}

	public void clickToChangeImage(WebDriver driver) {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
		
	}


	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWith= Integer.parseInt( getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight=Integer.parseInt( getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWith!=200)||(imageHeight!=200);
	}
	
	public void openTabSideBarByName(String tabname) {
		waitForElementClickable(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabname);
		clickToElement(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabname);
	}

}
