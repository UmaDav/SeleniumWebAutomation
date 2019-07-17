package pdms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InitiateAuthorizationPage {
	private static WebElement element = null;
	
	
	public static WebElement sel_PensionFor(WebDriver driver){
        element = driver.findElement(By.id("PensionFor"));
        return element;
          }
	public static WebElement tbl_PensionersSummary(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='D_PensionersDataSummary.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static WebElement tbl_PensionersData(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='D_PensionerDataList.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static WebElement btn_PaginationNext(WebDriver driver){
     	element = driver.findElement(By.name("pyGridPaginator_pyWorkPage.SearchPensioner_14"));   
     	return element; 
	}
}
