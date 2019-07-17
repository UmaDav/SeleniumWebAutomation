package whms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExistStorageOnboardingPage {
private static WebElement element = null;
	
	//**** Enter Personal Details ***///
	public static WebElement txt_FarmerName(WebDriver driver){
    	element = driver.findElement(By.id("FarmerName"));
    	return element;
         }
	
	public static WebElement txt_RepName(WebDriver driver){
    	element = driver.findElement(By.id("RepresentativeName"));
    	return element;
         }
	public static WebElement txt_MobileNumber(WebDriver driver){
    	element = driver.findElement(By.id("MobileNumber"));
    	return element;
         }
	
	//**** Enter Produce Details ***///
	public static WebElement sel_ProduceType(WebDriver driver){
    	element = driver.findElement(By.id("ProduceType"));
    	return element;
         }
	public static WebElement sel_ProduceSubType(WebDriver driver){
    	element = driver.findElement(By.id("ProduceSubType"));
    	return element;
         }
	public static WebElement txt_TotalProduce(WebDriver driver){
    	element = driver.findElement(By.id("TotalProduce"));
    	return element;
         }
	public static WebElement txt_ProduceInBags(WebDriver driver){
    	element = driver.findElement(By.id("ProduceInBags"));
    	return element;
         }
	
	//**** Enter Storage Details ***///
	public static WebElement calendar_StorageDate(WebDriver driver){
        	element = driver.findElement(By.xpath("//span[@name=\"$PpyWorkPage$pExistingStorageDetails$pStorageDetails$pStorageDate\"]"));
        	return element;
          }
		public static WebElement ProduceSubType(WebDriver driver){
	    	element = driver.findElement(By.id("ProduceSubType"));
	    	return element;
	         }
		public static WebElement txt_WareHouseID(WebDriver driver){
	    	element = driver.findElement(By.id("WareHouseID"));
	    	return element;
	         }
		public static WebElement txt_SpaceAllotted(WebDriver driver){
	    	element = driver.findElement(By.id("SpaceAllotted"));
	    	return element;
	         }
		
	//**** Button ***/////
		public static WebElement btn_generateStorageCard(WebDriver driver){
			element = driver.findElement(By.name("CollectProduceStorageDetails_pyWorkPage_516"));
	    	return element;
	         }	
	
}
