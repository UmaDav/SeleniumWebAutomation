package rbz.repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class AddFarmerPage {
	private static WebElement element = null;
	 
    public static WebElement txtbx_CardNumber(WebDriver driver){
     	element = driver.findElement(By.id("CardNumber"));    	
         return element; 
         }
    
    public static WebElement txtbx_FarmerName(WebDriver driver){
     	element = driver.findElement(By.id("FarmerName"));    
     	return element; 
         }
    
    public static WebElement txtbx_FarmerMobile(WebDriver driver){
     	element = driver.findElement(By.id("FarmerMobile"));    	
         return element; 
         }
    public static WebElement sel_District(WebDriver driver){
    	element = driver.findElement(By.id("District"));
    	return element;
         }
    
    public static WebElement sel_Mandal(WebDriver driver){
        element = driver.findElement(By.id("Mandal"));
        return element;
          }
    public static WebElement sel_Village(WebDriver driver){
        element = driver.findElement(By.id("Village"));
        return element;
          }
    public static WebElement sel_NomineeRelationship(WebDriver driver){
        element = driver.findElement(By.id("NomineeRelationship"));
        return element;
          }
    public static WebElement txtbx_FarmerNominee(WebDriver driver){
        element = driver.findElement(By.id("FarmerNominee"));         
          return element; 
          }
public static WebElement txtbx_NomineeMobile(WebDriver driver){
        element = driver.findElement(By.id("NomineeMobile"));         
          return element; 
          } 
	public static WebElement txtbx_PassbookNumber(WebDriver driver){
        element = driver.findElement(By.id("PassbookNumber"));         
          return element; 
          } 
	public static WebElement txtbx_AccountNumber(WebDriver driver){
        element = driver.findElement(By.id("AccountNumber"));         
          return element; 
          } 
	public static WebElement txtbx_Acres(WebDriver driver){
        element = driver.findElement(By.id("Acres"));         
          return element; 
          } 
	public static WebElement txtbx_Cents(WebDriver driver){
        element = driver.findElement(By.id("Cents"));         
          return element; 
          } 
	public static WebElement chbx_Vegetables(WebDriver driver){
        element = driver.findElement(By.id("D_RBZVegetablespxResults1pySelected1"));
        return element;
          }
	public static WebElement calendar_Cropstartdate(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@name='$PFarmerDetails$pCropStartDate']"));
        return element;
          }
	public static WebElement calendar_EOappdate(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@id='$PFarmerDetails$pEOApprovalDateSpan']/span"));
        return element;
          }
	public static WebElement sel_StallType(WebDriver driver){
        element = driver.findElement(By.id("TypeofShop"));
        return element;
          }
	public static WebElement btn_AddFarmer(WebDriver driver){
        element = driver.findElement(By.name("CollectRBZFarmerData_FarmerDetails_102"));
        return element;
          }
	public static WebElement lbl_AddFarmerConfirmation(WebDriver driver){
         element = driver.findElement(By.xpath("//*[@id=\"RULE_KEY\"]/div/div/div/div[1]/div/div/div/div"));
        return element;
          }
	public static WebElement btn_Close(WebDriver driver){
        element = driver.findElement(By.name("CollectRBZFarmerData_FarmerDetails_108"));
        return element;
          }
	
	public static WebElement div_ObjErr(WebDriver driver,String strObject){
        element = driver.findElement(By.xpath("//div[@id='"+ strObject +"']/span"));
        return element;
          }
	
	public static WebElement div_CropDetErr(WebDriver driver){
        element = driver.findElement(By.xpath("//p"));
        return element;
          }
	
	
	public static WebElement btn_PageClose(WebDriver driver){
        element = driver.findElement(By.id("container_close"));
        return element;
          }
   
}
