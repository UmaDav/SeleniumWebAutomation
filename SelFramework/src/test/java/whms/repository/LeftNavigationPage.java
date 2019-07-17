package whms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftNavigationPage {

	private static WebElement element = null;
	
	
	public static WebElement lnk_WarehouseManagement(WebDriver driver){
    	element = driver.findElement(By.linkText("Warehouse Management"));
    	return element;
	}
	public static WebElement lnk_ExStorageDetOnboard(WebDriver driver){
    	element = driver.findElement(By.linkText("Existing Storage Details Onboarding"));
    	return element;
	}
}
