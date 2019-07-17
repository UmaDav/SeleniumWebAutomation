package pdms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateDAPage {
private static WebElement element = null;
	
	
//**** fetch Work Object Id from Page ***///
	public static WebElement span_DAObjectId(WebDriver driver){
  	element = driver.findElement(By.xpath("//div /span[contains(text(),'Update DA')]/following::span"));
  	return element;
       }
	
	public static WebElement span_DAPercentage(WebDriver driver){
    	element = driver.findElement(By.xpath("//div/b[contains(text(),'Current DA %')]/following::span"));
    	return element;
	}
	public static WebElement txtbx_NewDA(WebDriver driver){
    	element = driver.findElement(By.id("NewDA"));
    	return element;
	}
	public static WebElement calendar_FromDate(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[@name='$PpyWorkPage$pEffectiveFrom']"));
    	return element;
	}
	public static WebElement span_ToDate(WebDriver driver){
    	element = driver.findElement(By.xpath("//div/b[contains(text(),'To Date')]/following::span"));
    	return element;
	}
	
	public static WebElement span_DAforMonths(WebDriver driver){
    	element = driver.findElement(By.xpath("//div/b[contains(text(),'DA for months')]/following::span"));
    	return element;
	}
	
	public static WebElement btn_Action(WebDriver driver, String strAction){
     	element = driver.findElement(By.xpath("//button[contains(.,'"+ strAction+"')]"));   
     	return element; 
	}
	public static WebElement btn_PaginationNext(WebDriver driver){
     	element = driver.findElement(By.name("pyGridPaginator_pyPortalHarness.SearchPensioner_14"));   
     	return element; 
	}
	public static WebElement span_Confirm(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Please Confirm')]"));
    	return element;
	}
	public static WebElement span_ConfirmMsg(WebDriver driver, String strMsg){
     	element = driver.findElement(By.xpath("//div[contains(text(),'"+strMsg+"')]"));    
     	return element; 
         }
	public static WebElement span_MsgPendingInfo(WebDriver driver, String strMsg){
     	element = driver.findElement(By.xpath("//span[contains(text(),'"+strMsg +"')]"));    
     	return element; 
         }
	public static WebElement span_DAReadonlyField(WebDriver driver, String strHeader){
     	element = driver.findElement(By.xpath("//div/b[contains(text(),'"+strHeader+"')]/following::span"));    
     	return element; 
         }
	
	 public static WebElement lnk_File(WebDriver driver, String strFile){
	     	element = driver.findElement(By.xpath("//a[@title='" + strFile + "']"));    
	     	return element; 
	         }
	 public static WebElement span_PDMSPendingTasks(WebDriver driver){
	     	element = driver.findElement(By.xpath("//a[@title='Click to open PDMS Pending Tasks']"));    
	     	return element; 
	         }
	
}
