package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageRewrite {

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
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		return explicitwait.until(ExpectedConditions.alertIsPresent());
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
	
	public void switchtoWindowByID(WebDriver driver, String parentID) {
		Set <String> allWindows= driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin=driver.getTitle();
			if(currentWin.equals(title)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if(!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);

	}
	
	public By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	public WebElement getWebElement(WebDriver driver,String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	public List <WebElement> getListWebElement(WebDriver driver,String xpathLocator){
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver,String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}
	
	public void sendKeyToElement(WebDriver driver,String xpathLocator,String textValue) {
		WebElement element= getWebElement(driver, xpathLocator);
		element.clear();
		element.click();
	}
}
