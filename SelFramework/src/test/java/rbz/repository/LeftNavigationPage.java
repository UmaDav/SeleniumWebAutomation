package rbz.repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LeftNavigationPage {
	private static WebElement element = null;
	
	 public static WebElement lnk_RythuBazaar(WebDriver driver){
	    	element = driver.findElement(By.linkText("Rythu Bazaar"));
	    	return element;
	    }
	 public static WebElement lnk_FarmerProfile(WebDriver driver){
	    	element = driver.findElement(By.linkText("Farmer Profile"));
	    	return element;
	    }
}
