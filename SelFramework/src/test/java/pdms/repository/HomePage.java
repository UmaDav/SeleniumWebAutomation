package pdms.repository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebElement element = null;
	
		
	public static WebElement tab_Options(WebDriver driver,String strTabName){
        element = driver.findElement(By.xpath("//b[contains(text(),'" + strTabName + "')]"));
        return element;
          }

	public static WebElement span_Reports(WebDriver driver){
        element = driver.findElement(By.xpath("//span[contains(text(),'Reports')]"));
        return element;
          }
	public static WebElement tbl_AddPensioners(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListAddNewPensionerBB.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static List<WebElement> tbl_AddPenRows(WebDriver driver){
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListAddNewPensionerBB.pxResults']/tbody/tr" ));
        return elements;
          }
	public static WebElement span_SearchTablecell(WebDriver driver,String strValue){
        element = driver.findElement(By.xpath("//a[contains(text(),'"+ strValue+"')]"));
        return element;
          }
	
	public static WebElement btn_AddPenPaginationNext(WebDriver driver){
        //element = driver.findElement(By.name("//pyGridPaginator_pyDisplayHarness_14"));
		element = driver.findElement(By.name("pyGridPaginator_pyPortalHarness_14"));
        return element;
          }
	public static WebElement btn_AddPenPaginationElement(WebDriver driver, int ia){
		//element = driver.findElement(By.xpath("//div[@class='field-item dataValueWrite']/a[contains(text(),'"+ia+"')]"));
		element = driver.findElement(By.xpath("//div[@class='field-item dataValueWrite']/a[@aria-label='Page "+ia +"']"));
		return element;
          }
	
	public static WebElement btn_UpdatDAPaginationNext(WebDriver driver){
     	element = driver.findElement(By.name("//pyGridPaginator_pyPortalHarness_14"));      
     	return element; 
         }
	public static WebElement btn_UpdatIRPaginationNext(WebDriver driver){
     	element = driver.findElement(By.name("//pyGridPaginator_pyPortalHarness_15"));      
     	return element; 
         }
	public static WebElement tbl_UpdateDA(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListWidgetUpdateDABB.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static List<WebElement> tbl_UpdateDARows(WebDriver driver){
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListWidgetUpdateDABB.pxResults']/tbody/tr"));
        return elements;
          }
	public static WebElement tbl_UpdateIR(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListWidgetUpdateIRBB.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static List<WebElement> tbl_UpdateIRRows(WebDriver driver){
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='pgRepPgSubSectionWorkListWidgetUpdateIRBB.pxResults']/tbody/tr"));
        return elements;
          }
	public static WebElement menu_DownArrow(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@class='pi pi-caret-solid-down layout-group-tablist-menu-nav']"));
        return element;
          }
	public static WebElement sel_Reports_PensionerStatus(WebDriver driver){
        element = driver.findElement(By.id("PensionerStatus"));
        return element;
          }
	public static WebElement tbl_Report_PensionersData(WebDriver driver, int tr_id, int td_id){
        element = driver.findElement(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='D_PensionerDataList.pxResults']/tbody/tr["+ 
        		tr_id +"]/td["+ td_id +"]"));
        return element;
          }
	public static int tbl_Report_PensionersRows(WebDriver driver){
        int rowcount = driver.findElements(By.xpath("//*[@id='bodyTbl_right' and @pl_prop='D_PensionerDataList.pxResults']/tbody/tr")).size();
        return rowcount;
          }	
	public static WebElement btn_Action(WebDriver driver, String strAction){
     	element = driver.findElement(By.xpath("//button[contains(.,'"+ strAction+"')]"));   
     	return element; 
	}
		
}
