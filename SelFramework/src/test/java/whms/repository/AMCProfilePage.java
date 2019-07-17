package whms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AMCProfilePage {

private static WebElement element = null;
	
	//**** Select District ***///
	public static WebElement sel_District(WebDriver driver){
    	element = driver.findElement(By.id("District"));
    	return element;
         }
	public static WebElement sel_AMCYard(WebDriver driver){
    	element = driver.findElement(By.id("AMCYard"));
    	return element;
         }	
	public static WebElement rbtn_Warehouse(WebDriver driver){
		element = driver.findElement(By.xpath("//input[@name='$PpyWorkPage$pWarehouseManagerData$pFacilityType' and @type='radio' and @value='1']/following::label"));
    	return element;
         }	
	public static WebElement rbtn_Godwn(WebDriver driver){
		element = driver.findElement(By.xpath("//input[@name='$PpyWorkPage$pWarehouseManagerData$pFacilityType' and @type='radio' and @value='2']/following::label"));
    	return element;
         }
	public static WebElement txtbx_WhGodwnId(WebDriver driver){
		element = driver.findElement(By.id("WareHouseID"));
    	return element;
         }	
	public static WebElement txtbx_StorageCapacity(WebDriver driver){
		element = driver.findElement(By.id("StorageCapacity"));
    	return element;
         }
	public static WebElement btn_AddWarehouse(WebDriver driver){
		element = driver.findElement(By.name("CreateAMCProfile_pyWorkPage_25"));
    	return element;
         }
	public static WebElement span_Msg(WebDriver driver){
		element = driver.findElement(By.xpath("//div[@class='content-item content-label item-1 flex flex-row dataLabelWrite dashboard_heading1_dataLabelWrite']"));
    	return element;
         }
}
