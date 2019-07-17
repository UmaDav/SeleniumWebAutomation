package pdms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftNavigationPage {

	private static WebElement element = null;
	
	
	public static WebElement lnk_Home(WebDriver driver){
    	element = driver.findElement(By.linkText("Home"));
    	return element;
	}
	
	public static WebElement lnk_InitiateAuthorization(WebDriver driver){
    	element = driver.findElement(By.linkText("Initiate Authorization"));
    	return element;
	}
	
	public static WebElement lnk_UpdateDA(WebDriver driver){
    	element = driver.findElement(By.linkText("Update DA"));
    	return element;
	}
	public static WebElement lnk_EditPensioners(WebDriver driver){
    	element = driver.findElement(By.linkText("Edit Pensioners"));
    	return element;
	}
	
	 public static WebElement lnk_AddNewPensioner(WebDriver driver){
	    	element = driver.findElement(By.linkText("Add New Pensioner"));
	    	return element;
	    }
	 public static WebElement lnk_UpdateIR(WebDriver driver){
	    	element = driver.findElement(By.linkText("Update IR"));
	    	return element;
	    }
}
