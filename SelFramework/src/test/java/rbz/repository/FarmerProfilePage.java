package rbz.repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class FarmerProfilePage {

	private static WebElement element = null;
	
	 public static WebElement lbl_TotalFarmersCnt(WebDriver driver){	    	
	    	element = driver.findElement(By.name("RBZFarmerData_pyDisplayHarness_13"));
	    	return element;	 
	         } 
	 public static WebElement lbl_ActiveFarmersCnt(WebDriver driver){	    	
	    	element = driver.findElement(By.name("RBZFarmerData_pyDisplayHarness_14"));
	    	return element;	 
	         } 
	 public static WebElement lbl_InactiveFarmersCnt(WebDriver driver){	    	
	    	element = driver.findElement(By.name("RBZFarmerData_pyDisplayHarness_15"));
	    	return element;	 
	         } 
	 
	    public static WebElement btn_AddFarmer(WebDriver driver){	    	
	    	element = driver.findElement(By.xpath("//button[contains(.,'Add Farmer')]"));
	    	return element;	 
	         }
	    
	    public static WebElement txtbx_CardNumber(WebDriver driver){	    	
	    	element = driver.findElement(By.id("CardNumber"));
	    	return element;	 
	         }

	    public static WebElement txtbx_FarmerName(WebDriver driver){	    	
	    	element = driver.findElement(By.id("FarmerName"));
	    	return element;	 
	         }
	    
	    public static WebElement btn_Search(WebDriver driver){	    	
	    	element = driver.findElement(By.name("RBZTotalFarmerData_pyDisplayHarness_13"));
	    	return element;	 
	         }
   
	    public static WebElement frm_FarmerPage(WebDriver driver){	    	
	    	element = driver.findElement(By.cssSelector(".layout-content-inline_grid_double > .item-3"));
	    	return element;	 
	         }
	    
	    public static WebElement tbl_FarmersList(WebDriver driver, String strCardNumber){	    	
	    	element = driver.findElement(By.xpath("//span[contains(text(),'" + strCardNumber + "')]/parent::div//parent::td//parent::tr"));
	    	return element;	 
	         } 
	    
	    public static WebElement tbl_FarmersList_tdValue(WebDriver driver, String strCardNumber, int colNum){	    	
	    	element = driver.findElement(By.xpath("//span[contains(text(),'" + strCardNumber + "')]/parent::div//parent::td//parent::tr//td["+ colNum+"]"));
	    	return element;	 
	         } 
	    public static WebElement btn_PaginationNext(WebDriver driver){	    	
	    	element = driver.findElement(By.name("pyGridPaginator_pyDisplayHarness_14"));
	    	return element;	 
	         }
	    public static WebElement btn_PaginationPrevious(WebDriver driver){	    	
	    	element = driver.findElement(By.name("pyGridPaginator_pyDisplayHarness_2"));
	    	return element;	 
	         }
	
}
