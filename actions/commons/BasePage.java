package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.hrm.DashboardPO;
import pageObject.hrm.LoginPO;
import pageObject.hrm.PageGenerator;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.hrm.BasePageUI;
import pageUIs.hrm.MyInfoPageUI;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.user.BasePageNopcommerceUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openHomePageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver){
		return driver.manage().getCookies();
		
	}
	
	public void setCookies(WebDriver driver,Set<Cookie> cookies) {
		for(Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
		
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();

	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	
	
	public By getByLocator(String locatorType) {
		By by=null;
		if(locatorType.startsWith("id=")|| locatorType.startsWith("ID=")|| locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		}else if (locatorType.startsWith("class=")|| locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by=By.className(locatorType.substring(6));
		}else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by=By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by=By.cssSelector(locatorType.substring(4));
		}else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			by=By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not support");
		}
		return by;
	}
	
	public String getDynamicXpath(String locatorType, String...dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			locatorType= String.format(locatorType, (Object[])dynamicValues);
		}
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType){
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType , String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver,String locatorType, String textValue) {
		WebElement element= getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	
	public void sendkeyToElement(WebDriver driver,String locatorType, String textValue , String...dynamicValues) {
		WebElement element= getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select =new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...dynamicValues) {
		Select select =new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select= new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType,String...dynamicValues) {
		Select select= new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select= new Select(getWebElement(driver, locatorType));
		return select.isMultiple();

	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long second){
		try {
			Thread.sleep(second *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText().trim();
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String...dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element= getWebElement(driver, locatorType);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckbox(WebDriver driver, String locatorType,String...dynamicValues) {
		WebElement element= getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element= getWebElement(driver, locatorType);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType,String...dynamicValues) {
		WebElement element= getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	
	public boolean isElementDisplay(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		}catch (NoSuchElementException e) {
			return false;
		}
		
	}
	
	public boolean isElementDisplay(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideGlobalTimeout(driver, shorttimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		
		overrideGlobalTimeout(driver, longtimeout);
		if(elements.size()==0) {
			return true;
		}else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}
	
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action= new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		Actions action= new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType , Keys key) {
		Actions action= new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType),key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType , Keys key,String...dynamicValues) {
		Actions action= new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)),key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}


	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType,String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
		
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
		
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));

	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));

	}
	
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, shorttimeout);
		overrideGlobalTimeout(driver, shorttimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longtimeout);

	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
		
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longtimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		
	}
	
	public void uploadMultiplesFiles(WebDriver driver, String...fileNames) {
		String filePath=GlobalConstants.UPLOAD_FILE;
		String fullFileName="";
		for (String file : fileNames) {
			fullFileName = fullFileName +filePath+file+"\n";
		}
		fullFileName=fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopcommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopcommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopcommerceUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		switch(pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver) ;
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver) ;
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver) ;
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver) ;
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}
	
	public UserCustomerInforPageObject opencustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.CUSTOMER_INFOR_LINK);
		clickToElement(driver, BasePageNopcommerceUI.CUSTOMER_INFOR_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	
	
	/**
	 * Enter to dynamic textboxID
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID,textboxID );
		sendkeyToElement(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	
	/**
	 * Click to dynamic Button by text
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	/**
	 * Select Item in dropdown by Name attribute
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdown(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}
	
	/**
	 * Click to dynamic radio button by label name
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckbox(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	
	/**
	 * Click to dynamic checkbox by label name
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckbox(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	/**
	 * Get value in textbox by textboxID
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
	
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageNopcommerceUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopcommerceUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNopcommerceUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	/**
	 * Open menupage by name 
	 * @param driver
	 * @param Dynamic menu page
	 * @param menu page name
	 */
	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
	}
	
	/**
	 * Open sub menupage by name 
	 * @param driver
	 * @param Dynamic menu page
	 * @param menu page name, submenu page name
	 */
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
	}
	
	/**
	 * Open child sub menupage by name 
	 * @param driver
	 * @param Dynamic menu page
	 * @param menu page name, submenu page name, child submenu page name
	 */
	
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
		
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_PAGE, childSubMenuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_PAGE, childSubMenuPageName);


	}
	
	/**
	 * click to button by id 
	 * @param driver
	 * @param button by id
	 */
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
	}
	
	/**
	 * enter to textbox by id 
	 * @param driver
	 * @param textbox by id
	 * @param value
	 */
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxIDName);
	}
	
	/**
	 * get textbox value by id 
	 * @param driver
	 * @param textbox by id
	 * @param get value
	 */
	public String getTextboxvalueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);
	}
	
	/**
	 * select item dropdown by id 
	 * @param driver
	 * @param select item by id
	 */
	
	public void selectItemDropdownByID(WebDriver driver, String dropdownID, String valueItem) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropdownID);
	}
	
	/**
	 * get selected item in dropdown 
	 * @param driver
	 * @param dropdownID
	 * @param return dropdown selected text
	 */
	
	public String getItemDropdownByID(WebDriver driver, String dropdownID ) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
	}
	
	
	/**
	 * click to checkbox by label
	 * @param driver
	 * @param checkbox label name
	 */
	
	public void clickToCheckboxByLabelHRM(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckbox(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	/**
	 * click to radio by label
	 * @param driver
	 * @param radio label name
	 */
	
	public void clickToRadioByLabelHRM(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckbox(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public boolean isRadioSelectedByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, labelName);
	}
	
	public DashboardPO loginToSystem (WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}
	
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);
		waitForElementClickable(driver, BasePageUI.lOGOUT_LINK);
		clickToElement(driver, BasePageUI.lOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	
	public void uploadToChangeImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
		
	}
	
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_VALUE,messageValue);
		return isElementDisplay(driver, BasePageUI.SUCCESS_MESSAGE_VALUE,messageValue);
		
	}
	
	public boolean isFieldEnabledByName(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}
	
	/**
	 * get value in table at column and row index
	 * @param driver
	 * @param table id, headername, rowindex
	 */
	
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName )+1;
		
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		
	}
	
	
	private long longtimeout= GlobalConstants.LONG_TIMEOUT;
	private long shorttimeout= GlobalConstants.SHORT_TIMEOUT;
	
}
