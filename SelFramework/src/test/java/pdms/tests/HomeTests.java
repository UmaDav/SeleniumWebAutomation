package pdms.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utilities.Base;

import pdms.repository.HomePage;
import pdms.repository.InitiateAuthorizationPage;
import pdms.repository.LeftNavigationPage;

public class HomeTests extends Base{	

	public static void AddPensionerSearchTest(String strRowValue) throws InterruptedException {
		//LeftNavigationPage.lnk_Home(driver).click();
		Thread.sleep(6000);
		HomePage.tab_Options(driver, "Add Pensioners").click();
		Thread.sleep(2000);
		Boolean flag = true;
		//String strRowValue=reader.getCellData("AddPensioner", "ObjectID", rowNum);
		int PageNum=1;
		 List<WebElement> alinks = driver.findElements(By.xpath("//div[@class='field-item dataValueWrite']/a"));
			int pgcount = alinks.size();
		 while(PageNum<=pgcount) {		
		//while(flag == true) {
		if (chkPenNumber(strRowValue) == true) {
			HomePage.span_SearchTablecell(driver,strRowValue).click();
			PageNum = pgcount=1;
			//flag = false;
			}
		else {
			if(PageNum!=pgcount) {
				PageNum++;
				//HomePage.btn_AddPenPaginationElement(driver,PageNum).click();
				driver.findElement(By.xpath("//div[@class='field-item dataValueWrite']//a[contains(text(),'2 ')]")).click();
				//clickOn(driver,HomePage.btn_AddPenPaginationElement(driver,PageNum),10);
				}
	    	Thread.sleep(5000);	    	
	    	//PageNum++;			 
			//flag = true; 
			}
		}
		//end while loop
		}
	
	public static void UpdateDASearch(String strRowValue) throws InterruptedException {
		//LeftNavigationPage.lnk_Home(driver).click();
		Thread.sleep(6000);
		HomePage.tab_Options(driver, "Update DA").click();
		Thread.sleep(2000);
		//Boolean flag = true;
		//String strRowValue=reader.getCellData("AddPensioner", "ObjectID", rowNum);
		int PageNum=1;
		 List<WebElement> alinks = driver.findElements(By.xpath("//div[@class='field-item dataValueWrite']/a"));
			int pgcount = alinks.size();
		 while(PageNum<=pgcount) {			 
		//while(flag == true) {
		if (chkDANumber(strRowValue) == true) {
			HomePage.span_SearchTablecell(driver,strRowValue).click();
			PageNum=pgcount+1;
			}
		else {			
			if(PageNum!=pgcount) {clickOn(driver,HomePage.btn_UpdatDAPaginationNext(driver),10);}
	    	Thread.sleep(5000);	    	
	    	PageNum++;			 
			//Thread.sleep(5000);
			//flag = true; 
			}
		}
		//end while loop
		}
	public static void UpdateIRSearch(String strRowValue) throws InterruptedException {
		//LeftNavigationPage.lnk_Home(driver).click();
		Thread.sleep(6000);
		HomePage.tab_Options(driver, "Update IR").click();
		Thread.sleep(2000);
		Boolean flag = true;
		while(flag == true) {
		if (chkIRNumber(strRowValue) == true) {
			HomePage.span_SearchTablecell(driver,strRowValue).click();
			flag = false;}
		else {
			HomePage.btn_UpdatIRPaginationNext(driver).click();
			Thread.sleep(5000);
			flag = true; }
		}
		//end while loop
		}
	
	public static Boolean chkPenNumber(String strPenNumber) {
		for(int rowNum=2 ; rowNum<=HomePage.tbl_AddPenRows(driver).size() ; rowNum++) {			
			if (strPenNumber.equalsIgnoreCase(HomePage.tbl_AddPensioners(driver, rowNum, 1).getText())) {
				return true;
			}
		}		
		return false;
	}
	
	public static Boolean chkDANumber(String strDAID) {
		for(int rowNum=2 ; rowNum<=HomePage.tbl_UpdateDARows(driver).size(); rowNum++) {			
			if (strDAID.equalsIgnoreCase(HomePage.tbl_UpdateDA(driver, rowNum, 1).getText())) {
				return true;
			}
		}		
		return false;
	}
		
	public static Boolean chkIRNumber(String strIRID) {
		for(int rowNum=2 ; rowNum<=HomePage.tbl_UpdateIRRows(driver).size() ; rowNum++) {			
			if (strIRID.equalsIgnoreCase(HomePage.tbl_UpdateIR(driver, rowNum, 1).getText())) {
				return true;
			}
		}		
		return false;
	}
	}

